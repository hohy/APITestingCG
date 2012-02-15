package cz.cvut.fit.hybljan2.apitestingcg.ngenerator;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.BlacklistRule;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.io.File;
import java.util.Iterator;

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

    // defines package name for currently generated package content
    protected String currentPackageName;

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
     * After visiting all classes in the package, directory for the package is created and classes from cuBuffer are generated.
     * @param apiPackage
     */
    @Override
    public void visit(APIPackage apiPackage) {
        currentPackageName = generateName(jobConfiguration.getOutputPackage(), apiPackage.getName());
        // create directory for package
        File outputDir = new File(jobConfiguration.getOutputDir() + File.separatorChar + getPathToPackage(currentPackageName));
        if(!outputDir.exists()) outputDir.mkdirs();

        for(APIClass cls : apiPackage.getClasses()) {
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

    protected String getPathToPackage(String pkgName) {
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
                if((rule.getRule().contains(itemSignature)||(itemSignature.contains(rule.getRule()))) && (rule.getItem().equals(target) || rule.getItem().equals(WhitelistRule.RuleItem.ALL))) {
                    enabled = true;
                    break;
                }
            }
        } else enabled = true;  // if there is no rules for whitelist, enable all items.

        for(BlacklistRule rule : jobConfiguration.getBlacklistRules()) {
            if((rule.getRule().contains(itemSignature)||(itemSignature.contains(rule.getRule()))) && (rule.getItem().equals(target) || rule.getItem().equals(WhitelistRule.RuleItem.ALL))) {
                enabled = false;    // disable item if there is blacklist rule for it.
                break;
            }
        }

        return enabled;
    }

    /**
     * Generates unique identification string for method. Used for identification in blacklist and whitelist
     * in configuration.
     * @param method
     * @param className
     * @return
     */
    protected String methodSignature(APIMethod method, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(".");
        if(method.getName().contains(".")) sb.append(method.getName().substring(method.getName().lastIndexOf(".")+1));
        else sb.append(method.getName());
        sb.append('(');
        // list of Params
        Iterator iter = method.getParameters().iterator();
        if (iter.hasNext()) {
            sb.append(iter.next());
            while (iter.hasNext()) {
                sb.append(", ");
                sb.append(iter.next());
            }
        }
        sb.append(')');
        String result = sb.toString();
        return sb.toString();
    }


}
