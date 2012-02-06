package cz.cvut.fit.hybljan2.apitestingcg.ngenerator;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.Pretty;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    protected Symtab symtab;

    protected JCTree.JCClassDecl currentClass;

    public Generator(GeneratorConfiguration configuration) {
        Context ctx = new Context();
        Options opt = Options.instance(ctx);
        JavaCompiler compiler = JavaCompiler.instance(ctx);
        maker = TreeMaker.instance(ctx);
        names = new SharedNameTable(new com.sun.tools.javac.util.Names(ctx));
        symtab  = Symtab.instance(ctx);
        this.configuration = configuration;
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
        ListBuffer<JCTree> packageBuffer = new ListBuffer<JCTree>();

        // create directory for package
        File outputDir = new File(jobConfiguration.getOutputDir() + File.separatorChar + getPathToPackage(packageName));
        if(!outputDir.exists()) outputDir.mkdirs();

        for(APIClass cls : apiPackage.getClasses()) {            
            cls.accept(this);
            packageBuffer.add(currentClass);
            JCTree.JCCompilationUnit currentPackage = maker.TopLevel(packageAnnotations, packageNameExpression, packageBuffer.toList());

            String clsFileName = outputDir.getPath() + File.separator + currentClass.getSimpleName() + ".java";
            FileWriter fw = null;
            try {
                fw = new FileWriter(clsFileName);
                Pretty pretty = new Pretty(fw, true);
                pretty.printUnit(currentPackage, currentClass);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } finally {
                if(fw != null) try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
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

        //parameter name
        Name paramName = names.fromString(name);

        return maker.VarDef(mods, paramName, stringType, null);
    }

    public void generate(API api, GeneratorJobConfiguration job) {
        jobConfiguration = job;
        visit(api);
    }

    /**
     * Check if given item is enabled for generating in white and black lists in jobConfiguration.
     * @param itemSignature     Unique string that can be used for identification of the item. For class it's
     *                          full class name (with package name), for methods it's string containing className,
     *                          methodName, parameters and return type.
     * @param target
     * @return
     */
    protected boolean isEnabled(String itemSignature, WhitelistRule.RuleItem target) {
        boolean enabled = false;
        if(jobConfiguration.getWhitelistRules().size() > 0) {
            for(WhitelistRule rule : jobConfiguration.getWhitelistRules()) {
                if(rule.getRule().contains(itemSignature) && rule.getItem().equals(target)) {
                    enabled = true;
                    break;
                }
            }
        } else enabled = true;  // if there is no rules for whitelist, enable all items.

        return enabled;
    }
}
