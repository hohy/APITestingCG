package cz.cvut.fit.hybljan2.apitestingcg.ngenerator;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.IAPIVisitor;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;

import java.io.File;

/**
 * Abstract class defining Generator for API
 * User: Jan Hýbl
 * Date: 3.2.12
 * Time: 17:05
 */
public abstract class Generator implements IAPIVisitor {

    protected GeneratorJobConfiguration jobConfiguration;
    protected GeneratorConfiguration configuration;

    protected TreeMaker maker;
    protected Name.Table names;

    protected ListBuffer<JCTree> packageBuffer;

    public Generator() {
        Context ctx = new Context();
        Options opt = Options.instance(ctx);
        JavaCompiler compiler = JavaCompiler.instance(ctx);
        maker = TreeMaker.instance(ctx);
        names = new SharedNameTable(new com.sun.tools.javac.util.Names(ctx));
    }

    /**
     * Process all packages in given API.
     * @param api
     */
    @Override
    public void visit(API api) {
        for(APIPackage pkg : api.getPackages()) {
            pkg.accept(this);
        }
    }

    /**
     * For every original package from API creates new test package where test classes will be placed.
     * New packages are named by original name of package and configuration string from jobConfiguration field.
     * After visiting all classes in the package, directory for the package is created and classes from packageBuffer are generated.
     * @param apiPackage
     */
    @Override
    public void visit(APIPackage apiPackage) {

        List<JCTree.JCAnnotation> packageAnnotations = List.nil();
        String packageName = generateName(jobConfiguration.getOutputPackage(), apiPackage.getName());
        JCTree.JCExpression packageNameExpression = maker.Ident(names.fromString(packageName));
        packageBuffer = new ListBuffer<JCTree>();

        // create directory for package
        File outputDir = new File("output" + File.separatorChar + getPathToPackage(packageName));
        if(!outputDir.exists()) outputDir.mkdirs();

        for(APIClass cls : apiPackage.getClasses()) {
            JCTree.JCCompilationUnit currentPackage = maker.TopLevel(packageAnnotations, packageNameExpression, packageBuffer.toList());
            cls.accept(this);
        }


    }


    /**
     * Can be used for generating names of methods and classes. Replace "%s" sequence with {@code originalName}.
     * @param pattern         All "%s" sequences will be replaced with originalName
     * @param originalName    Original name of class/method.
     * @return                new name
     */
    protected String generateName(String pattern, String originalName) {
        return pattern.replaceAll("%s", originalName);
    }

    private String getPathToPackage(String pkgName) {
        return pkgName.replace('.', File.separatorChar);
    }


    /**
     * Makes JCTree.method parameter declaration node with given name and type.
     * @param name  Name of the parameter
     * @param type  Name of type of the parameter.
     * @return
     */
    protected JCTree.JCVariableDecl makeParameter(String name, String type) {
        JCTree.JCModifiers mods = maker.Modifiers(Flags.PARAMETER);

        //base type "String"
        JCTree.JCIdent stringType = maker.Ident(names.fromString(type));

        //array of String
        JCTree.JCArrayTypeTree arrayType = maker.TypeArray(stringType);

        //parameter name
        Name paramName = names.fromString(name);

        return maker.VarDef(mods, paramName, arrayType, null);
    }    
}
