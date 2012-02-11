package cz.cvut.fit.hybljan2.apitestingcg.ngenerator;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.io.File;
import java.util.Iterator;

import static com.sun.tools.javac.tree.JCTree.JCExpression;
import static com.sun.tools.javac.tree.JCTree.JCTypeParameter;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 3.2.12
 * Time: 17:07
 */
public class ExtenderGenerator extends ClassGenerator {

    private ListBuffer<JCTree> methodsBuffer;
    private Name clsName;
    private String visitingClassName;

    public ExtenderGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void visit(APIClass apiClass) {

        // check if extender for this class is enabled in jobConfiguration.
        if(!isEnabled(apiClass.getFullName(), WhitelistRule.RuleItem.EXTENDER)) return;

        initCompilationUnit();

        // Extender class will be public
        JCTree.JCModifiers modifiers = maker.Modifiers(Flags.PUBLIC);

        List<JCTree.JCExpression> implementing = List.nil();
        JCTree.JCExpression extending = null;
        List<JCTree.JCTypeParameter> typeParameters = List.nil();
        methodsBuffer = new ListBuffer<JCTree>();

        // if tested item is interface, create Implementator, otherwise Extender
        String pattern = null;
        if(apiClass.getType() == APIItem.Kind.INTERFACE) {
            pattern = configuration.getImplementerClassIdentifier();
            implementing.add(maker.Ident(names.fromString(apiClass.getName())));
        } else {
            pattern = configuration.getExtenderClassIdentifier();
            extending = maker.Ident(names.fromString(simplifyName(apiClass.getName())));
        }

        visitingClassName = apiClass.getFullName();
        clsName = names.fromString(generateName(pattern, apiClass.getName()));

        for(APIMethod constructor : apiClass.getConstructors()) {
            visitConstructor(constructor);
        }
        
        for(APIMethod method : apiClass.getMethods()) {
            method.accept(this);
        }

        JCTree.JCClassDecl currentClass = maker.ClassDef(modifiers, clsName, typeParameters, extending, implementing, methodsBuffer.toList());
        cuBuffer.add(currentClass);
        String classFilePath = jobConfiguration.getOutputDir() + File.separatorChar + getPathToPackage(currentPackageName) + File.separator + currentClass.getSimpleName() + ".java";
        generateSourceFile(classFilePath, currentClass);
    }

    private void visitConstructor(APIMethod constructor) {
        if(!isEnabled(methodSignature(constructor,visitingClassName), WhitelistRule.RuleItem.EXTENDER)) return;
        JCTree.JCModifiers modifiers = maker.Modifiers(Flags.PUBLIC);
        ListBuffer<JCTree.JCVariableDecl> params = new ListBuffer<JCTree.JCVariableDecl>();   
        Name methodName = names.names.init;
        JCTree.JCExpression methodType = maker.Type(symtab.voidType);

        char paramName = 'a';
        for(String param : constructor.getParameters()) {
            params.append(makeParameter(String.valueOf(paramName), simplifyName(param)));
            paramName++;
        }

        ListBuffer<JCTree.JCStatement> stmts = new ListBuffer<JCTree.JCStatement>();
        JCTree.JCExpressionStatement stmt = maker.Exec(makeSuperCall(params.toList()));
        stmts.add(stmt);
        JCTree.JCBlock body = maker.Block(0, stmts.toList());

        JCTree.JCMethodDecl cdecl = maker.MethodDef(modifiers, methodName, methodType, List.<JCTypeParameter>nil(), params.toList(), List.<JCExpression>nil(), body, null);
        methodsBuffer.add(cdecl);

    }

    @Override
    public void visit(APIField apiField) {
        //To hange body of implemented methods use File | Settings | File Templates.

    }

    /**
     * Generates AST of method overriding some method from original class.
     * @param method
     */
    @Override
    public void visit(APIMethod method) {
        if(!isEnabled(methodSignature(method, visitingClassName), WhitelistRule.RuleItem.EXTENDER)) return;

        ListBuffer<JCTree.JCAnnotation> annotations = new ListBuffer<JCTree.JCAnnotation>();
        annotations.add(maker.Annotation(maker.Type(symtab.overrideType), List.<JCExpression>nil()));
        JCTree.JCModifiers modifiers = maker.Modifiers(Flags.PUBLIC, annotations.toList());  // overriding method can have public modifier although original method can be protected.
        ListBuffer<JCTree.JCVariableDecl> params = new ListBuffer<JCTree.JCVariableDecl>();
        Name methodName = names.fromString(method.getName());
        JCTree.JCExpression methodType = maker.Type(symtab.voidType);

        char paramName = 'a';
        for(String param : method.getParameters()) {
            params.append(makeParameter(String.valueOf(paramName), simplifyName(param)));
            paramName++;
        }

        ListBuffer<JCTree.JCStatement> stmts = new ListBuffer<JCTree.JCStatement>();
        JCTree.JCExpressionStatement stmt = maker.Exec(makeSuperCall(params.toList()));
        stmts.add(stmt);
        JCTree.JCBlock body = maker.Block(0, stmts.toList());

        JCTree.JCMethodDecl cdecl = maker.MethodDef(modifiers, methodName, methodType, List.<JCTypeParameter>nil(), params.toList(), List.<JCExpression>nil(), body, null);
        methodsBuffer.add(cdecl);
    }

    /**
     * Makes JCTree node with super() call in constructor.
     * Example: super(a, b);
     * @param params    List of parameters of constructor.
     * @return          JCExpression of super() call.
     */
    private JCExpression makeSuperCall(List<JCTree.JCVariableDecl> params) {
        JCExpression exp = maker.Ident(names.fromString("super"));
        ListBuffer<JCExpression> args = new ListBuffer<JCExpression>();
        for(JCTree.JCVariableDecl param : params) {
            args.add(maker.Ident(param.getName()));
        }
        return maker.Apply(List.<JCExpression> nil(), exp, args.toList());
    }
}
