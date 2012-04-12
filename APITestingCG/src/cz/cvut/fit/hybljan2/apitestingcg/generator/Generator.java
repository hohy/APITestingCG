package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.BlacklistRule;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.2.12
 * Time: 13:59
 */
public abstract class Generator implements IAPIVisitor {

    protected static final char CLOSE_TYPE_ARGS = '\uFFFF';

    protected GeneratorConfiguration configuration;
    protected GeneratorJobConfiguration jobConfiguration;

    protected JCodeModel cm;
    private Map<String, JClass> classMap;
    private API currentAPI;

    // defines package name for currently generated package content
    protected String currentPackageName;

    public Generator(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public void generate(API api, GeneratorJobConfiguration job) {
        jobConfiguration = job;
        cm = new JCodeModel();
        classMap = new HashMap<String, JClass>();
        currentAPI = api;
        // create directory for package
        File outputDir = new File(jobConfiguration.getOutputDir());
        if (!outputDir.exists()) outputDir.mkdirs();
        visit(api);
        try {
            cm.build(outputDir);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Process all packages in given API.
     *
     * @param api
     */
    @Override
    public void visit(API api) {
        for (APIPackage pkg : api.getPackages()) {
            pkg.accept(this);
        }
    }

    /**
     * For every original package from API creates new test package where test classes will be placed.
     * New packages are named by original name of package and configuration string from jobConfiguration field.
     * After visiting all classes in the package, directory for the package is created and classes from cuBuffer are generated.
     *
     * @param apiPackage
     */
    @Override
    public void visit(APIPackage apiPackage) {
        currentPackageName = jobConfiguration.getOutputPackage().replaceAll("%s", apiPackage.getName());
        if (!isEnabled(apiPackage.getName(), WhitelistRule.RuleItem.ALL) || (jobConfiguration.isSkipDeprecated() && apiPackage.isDepreacated())) {
            return;
        }
        for (APIClass cls : apiPackage.getClasses()) {
            cls.accept(this);
        }


    }

    /**
     * Can be used for generating names of methods and classes. Replace "%s" sequence with {@code originalName}.
     * If original class is generic, method remove generics from class name.
     *
     * @param pattern      All "%s" sequences will be replaced with originalName
     * @param originalName Original name of class/method.
     * @return new name
     */
    protected static String generateName(String pattern, String originalName) {
        try {
            // escape $ char.
            originalName = originalName.replaceAll("\\$", "\\\\\\$");
            if (originalName.contains("<") && originalName.contains(">")) {
                String name = originalName.substring(0, originalName.indexOf("<"));
                return pattern.replaceAll("%s", simplifyName(name));
            } else {
                return pattern.replaceAll("%s", simplifyName(originalName));
            }
        } catch (IllegalArgumentException e) {
            System.err.printf("Can't generate name using pattern \"%s\" and original name \"%s\".", pattern, originalName);
        }
        return null;
    }

    /**
     * Check if given item is enabled for generating in white and black lists in jobConfiguration.
     *
     * @param itemSignature Unique string that can be used for identification of the item. For class it's
     *                      full class name (with package name), for methods it's string containing className,
     *                      methodName, parameters and return type.
     * @param target
     * @return
     */
    protected boolean isEnabled(String itemSignature, WhitelistRule.RuleItem target) {
        boolean enabled = false;
        if (jobConfiguration.getWhitelistRules().size() > 0) {
            for (WhitelistRule rule : jobConfiguration.getWhitelistRules()) {
                if ((rule.getRule().contains(itemSignature) || (itemSignature.contains(rule.getRule())))
                        && (rule.getItem().equals(target) || rule.getItem().equals(WhitelistRule.RuleItem.ALL))) {
                    enabled = true;
                    break;
                }
            }
        } else enabled = true;  // if there is no rules for whitelist, enable all items.

        for (BlacklistRule rule : jobConfiguration.getBlacklistRules()) {
            if ((rule.getRule().equals(itemSignature) || (itemSignature.contains(rule.getRule()))) && (rule.getItem().equals(target) || rule.getItem().equals(WhitelistRule.RuleItem.ALL))) {
                enabled = false;    // disable item if there is blacklist rule for it.
                break;
            }
        }

        return enabled;
    }

    /**
     * Generates unique identification string for method. Used for identification in blacklist and whitelist
     * in configuration.
     *
     * @param method
     * @param className
     * @return
     */
    protected String methodSignature(APIMethod method, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(".");
        if (method.getName().contains("."))
            sb.append(method.getName().substring(method.getName().lastIndexOf(".") + 1));
        else sb.append(method.getName());
        sb.append('(');
        // list of Params
        Iterator<APIMethodParameter> iter = method.getParameters().iterator();
        if (iter.hasNext()) {
            sb.append(iter.next().getType());
            while (iter.hasNext()) {
                sb.append(", ");
                sb.append(iter.next().getType());
            }
        }
        sb.append(')');
        String result = sb.toString();
        return sb.toString();
    }

    /**
     * Returns default value for given type.
     *
     * @param name
     * @return
     */
    public JExpression getPrimitiveValue(String name) {
        if (name.equals("byte")) return JExpr.cast(cm.BYTE, JExpr.lit(0));
        if (name.equals("short")) return JExpr.cast(cm.SHORT, JExpr.lit(0));
        if (name.equals("int")) return JExpr.lit(0);
        if (name.equals("long")) return JExpr.lit(0L);
        if (name.equals("float")) return JExpr.lit(0.0F);
        if (name.equals("double")) return JExpr.lit(0.0D);
        if (name.equals("boolean")) return JExpr.lit(false);
        if (name.equals("char")) return JExpr.lit('a');
        return JExpr._null();
    }

    /**
     * Returns default value for given type.
     *
     * @param name
     * @return
     */
    public static String getPrimitiveValueString(String name) {
        if (name.equals("byte")) return "0";
        if (name.equals("short")) return "0";
        if (name.equals("int")) return "0";
        if (name.equals("long")) return "0";
        if (name.equals("float")) return "0.0";
        if (name.equals("double")) return "0.0";
        if (name.equals("boolean")) return "false";
        if (name.equals("char")) return "a";
        return "null";
    }

    protected JClass getClassRef(String className) {

        try {
            APIClass cls = currentAPI.findClass(className);
            if (cls.getModifiers().contains(APIModifier.Modifier.PROTECTED)) {
                className = className.substring(className.lastIndexOf('.') + 1);
            }
            if (cls.isNested()) {
                className.replace('$', '.');
            }
        } catch (ClassNotFoundException e) {
        }

        // check if it's array
        if (className.endsWith("[]")) {
            return getClassRef(className.substring(0, className.length() - 2)).array();
        }

        if (className.contains("<")) {
            className = className.substring(0, className.indexOf("<"));
        }
        if (classMap.containsKey(className)) {
            return classMap.get(className);
        } else {
            JClass classReference = cm.ref(className);
            classMap.put(className, classReference);
            return classReference;
        }
    }

    protected JClass getGenericsClassRef(String className) {

        try {
            APIClass cls = currentAPI.findClass(className);
            //System.out.println("Class found: " + className);
            if (cls.getModifiers().contains(APIModifier.Modifier.PROTECTED)) {
                className = className.substring(className.lastIndexOf('.') + 1);
            }
        } catch (ClassNotFoundException e) {
            //System.err.println("Class not found: " + className);
        }

        // check if it's arra<y
        if (className.endsWith("[]")) {
            return getClassRef(className.substring(0, className.length() - 2)).array();
        }

        if (classMap.containsKey(className)) {
            return classMap.get(className);
        } else {
            JClass classReference = cm.ref(className);
            classMap.put(className, classReference);
            return classReference;
        }
    }

    /**
     * Finds class with given name and checks if the class has public modifier.
     *
     * @param name full class name
     * @return true or false if class is public or not. If class is not found (it's not part of API), returns false
     */
    protected boolean isClassPublic(String name, APIClass currentClass) {
        Set<String> classNames = JFormatter.getTypesList(name);
        boolean result = true;
        for (String className : classNames) {
            try {
                APIClass c = currentAPI.findClass(className);
                if (!c.getModifiers().contains(APIModifier.Modifier.PUBLIC)) {
                    result = false;
                }
            } catch (ClassNotFoundException e) {
                try {
                    APIClass nc = currentClass.getNestedClass(name);
                    return nc.getModifiers().contains(APIModifier.Modifier.PUBLIC);
                } catch (ClassNotFoundException e1) {
                    System.err.println("isClassPublic() - Class not found: " + className);
                }
            }
        }

        return result;
    }

    /**
     * Finds class with given name and checks if the class has public modifier.
     *
     * @param name full class name
     * @return true or false if class is public or not. If class is not found (it's not part of API), returns false
     */
    protected boolean isClassPublicOrProtected(String name, APIClass currentClass) {

        Set<String> classNames = JFormatter.getTypesList(name);
        boolean result = true;
        for (String className : classNames) {
            try {
                APIClass c = currentAPI.findClass(className);
                if (!c.getModifiers().contains(APIModifier.Modifier.PUBLIC)
                        && !(c.getModifiers().contains(APIModifier.Modifier.PROTECTED))) {
                    result = false;
                }
            } catch (ClassNotFoundException e) {
                try {
                    APIClass nc = currentClass.getNestedClass(name);
                    return nc.getModifiers().contains(APIModifier.Modifier.PUBLIC);
                } catch (ClassNotFoundException e1) {
                    System.err.println("isClassPublicOrProtected() - Class not found: " + className);
                }
            }
        }
        return result;
    }

    /**
     * Search current API for class with given name.
     *
     * @param name full class name
     * @return class info
     * @throws ClassNotFoundException if class isn't part of API
     */
    protected APIClass findClass(String name) throws ClassNotFoundException {
        return currentAPI.findClass(name);
    }

    public static String simplifyName(String originalName) {
        if (originalName.contains(".")) return originalName.substring(originalName.lastIndexOf(".") + 1);
        return originalName;
    }
}
