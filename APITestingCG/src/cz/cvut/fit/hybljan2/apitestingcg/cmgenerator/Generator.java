package cz.cvut.fit.hybljan2.apitestingcg.cmgenerator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.2.12
 * Time: 13:59
 */
public abstract class Generator implements IAPIVisitor {

    protected GeneratorConfiguration configuration;
    protected GeneratorJobConfiguration jobConfiguration;

    protected JCodeModel cm = new JCodeModel();

    // defines package name for currently generated package content
    protected String currentPackageName;

    public Generator(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public void generate(API api, GeneratorJobConfiguration job) {
        jobConfiguration = job;
        // create directory for package
        File outputDir = new File(jobConfiguration.getOutputDir());
        if(!outputDir.exists()) outputDir.mkdirs();
        visit(api);
        try {
            cm.build(outputDir);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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

        for(APIClass cls : apiPackage.getClasses()) {
            cls.accept(this);
        }


    }


    /**
     * Can be used for generating names of methods and classes. Replace "%s" sequence with {@code originalName}.
     * If original class is generic, method remove generics from class name.
     * @param pattern         All "%s" sequences will be replaced with originalName
     * @param originalName    Original name of class/method.
     * @return                new name
     */
    protected static String generateName(String pattern, String originalName) {
        if(originalName.contains("<") && originalName.contains(">")) {
            String name = originalName.substring(0, originalName.indexOf("<"));
            return pattern.replaceAll("%s", name);
        } else return pattern.replaceAll("%s", originalName);
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

    /**
     * Returns default value for given type.
     * @param name
     * @return
     */
    protected JExpression getDefaultPrimitiveValue(String name) {
        if(name.equals("byte")) return JExpr.lit(0);
        if(name.equals("short")) return JExpr.lit(0);
        if(name.equals("int")) return JExpr.lit(0);
        if(name.equals("long")) return JExpr.lit(0);
        if(name.equals("float")) return JExpr.lit(0.0);
        if(name.equals("double")) return JExpr.lit(0.0);
        if(name.equals("boolean")) return JExpr.lit(false);
        if(name.equals("char")) return JExpr.lit('a');
        return JExpr._null();
    }

}
