package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

import java.util.Map;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.2.12
 * Time: 14:11
 */
public abstract class ClassGenerator extends Generator {
    /**
     * Code model of the generated class.
     */
    protected JDefinedClass cls;

    /**
     * Stack of generated classes. Used for generating of the nested classes.
     */
    protected Stack<JDefinedClass> classStack = new Stack<>();

    /**
     * Class from the scanned API that is currently processed.
     */
    protected APIClass visitingClass;


    protected JBlock fieldsMethodBlock;
    protected JExpression fieldsInstance;

    public ClassGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    public JDefinedClass declareNewClass(int classMods, String packageName, String className, boolean nested) throws JClassAlreadyExistsException {
        JDefinedClass result;
        if (nested) {
            result = classStack.peek()._class(classMods, className, ClassType.CLASS);
        } else {
            result = cm._class(classMods, packageName + '.' + className, ClassType.CLASS);
        }
        return result;
    }

    public String generateGenericsString(Map<String, String[]> typeParamsMap) {
        StringBuilder sb = new StringBuilder();
        int typesCounter = 0;
        for (String typeName : typeParamsMap.keySet()) {
            if (typesCounter > 0) {
                sb.append(", ");
            }
            sb.append(typeName);
            int boundsCounter = 0;
            for (String typeBound : typeParamsMap.get(typeName)) {
                if (!typeBound.equals("java.lang.Object")) {
                    JClass boundClass = getClassRef(typeBound);
                    if (boundsCounter > 0) {
                        sb.append(" & ");
                    } else {
                        sb.append(" extends ");
                    }
                    sb.append(boundClass.name());
                    boundsCounter++;
                }
            }
            typesCounter++;
        }

        return sb.toString();
    }
}
