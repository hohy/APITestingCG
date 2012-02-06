package cz.cvut.fit.hybljan2.apitestingcg.ngenerator;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.UnsharedNameTable;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;

import java.io.File;
import java.util.LinkedList;

import static com.sun.tools.javac.tree.JCTree.JCExpression;
import static com.sun.tools.javac.tree.JCTree.JCTypeParameter;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 3.2.12
 * Time: 17:07
 */
public class ExtenderGenerator extends Generator {

    private ListBuffer<JCTree> methodsBuffer;
    private Name clsName;

    @Override
    public void visit(APIClass apiClass) {
        
        JCTree.JCModifiers modifiers = maker.Modifiers(Flags.PUBLIC);

        String pattern = null;
        List<JCTree.JCExpression> implementing = List.nil();
        JCTree.JCExpression extending = null;
        List<JCTree.JCTypeParameter> typeParameters = List.nil();
        methodsBuffer = new ListBuffer<JCTree>();

        // if tested item is interface, create Implementator, otherwise Extender
        if(apiClass.getType() == APIItem.Kind.INTERFACE) {
            pattern = configuration.getImplementerClassIdentifier();
            implementing.add(maker.Ident(names.fromString(apiClass.getName())));
        } else {
            pattern = configuration.getExtenderClassIdentifier();
            extending = maker.Ident(names.fromString(apiClass.getName()));
        }

        clsName = names.fromString(generateName(pattern, apiClass.getName()));

        for(APIMethod constructor : apiClass.getConstructors()) {
            visitConstructor(constructor);
        }
        
        for(APIMethod method : apiClass.getMethods()) {
            method.accept(this);
        }

        JCTree.JCClassDecl clsBDecl = maker.ClassDef(modifiers, clsName, typeParameters, extending, implementing, methodsBuffer.toList());
        //File file = new File(outputDir.getPath() + File.separatorChar + clsName + ".java");

    }

    private void visitConstructor(APIMethod constructor) {
        JCTree.JCModifiers modifiers = maker.Modifiers(Flags.PUBLIC);
        ListBuffer<JCTree.JCVariableDecl> params = new ListBuffer<JCTree.JCVariableDecl>();        
        char paramName = 'a';
        for(String param : constructor.getParameters()) {
            params.append(makeParameter(String.valueOf(paramName), param));
            paramName++;
        }

        ListBuffer<JCTree.JCStatement> stmts = new ListBuffer<JCTree.JCStatement>();
        JCTree.JCExpressionStatement stmt = maker.Exec(makeSuperCall(params.toList()));
        stmts.add(stmt);
        JCTree.JCBlock body = maker.Block(0, stmts.toList());
        maker.MethodDef(modifiers, clsName, null,List.<JCTypeParameter> nil(), params.toList(),List.<JCExpression> nil(), body, null);
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

    @Override
    public void visit(APIField apiField) {
        //To change body of implemented methods use File | Settings | File Templates.
        
    }

    @Override
    public void visit(APIPackage apiPackage) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(APIMethod method) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
