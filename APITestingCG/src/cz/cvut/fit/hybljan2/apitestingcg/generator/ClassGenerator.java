package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpression;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.2.12
 * Time: 14:11
 */
public abstract class ClassGenerator extends Generator {

    protected JDefinedClass cls;
    protected APIClass visitingClass;
    protected JBlock fieldsMethodBlock;
    protected JExpression fieldsInstance;

    public ClassGenerator(GeneratorConfiguration configuration) {
        super(configuration);
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
