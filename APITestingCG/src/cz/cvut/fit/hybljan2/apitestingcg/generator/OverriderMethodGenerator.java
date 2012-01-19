package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 19.1.12
 * Time: 10:53
 */
public class OverriderMethodGenerator extends MethodGenerator {

    public OverriderMethodGenerator(APIMethod method) {
        setModifiers("public");
        setName(method.getName());
        setReturnType(method.getReturnType());

        StringBuilder sb = new StringBuilder();
        for (APIModifier.Modifier m : method.getModifiers()) {
            if(!m.equals(APIModifier.Modifier.ABSTRACT)) {
                sb.append(m.toString().toLowerCase()).append(" ");
            }
        }
        setModifiers(sb.toString().trim());
        setThrown(method.getThrown());
        addAnotation("Override");
        setParams(getMethodParamList(method));
        setBody("\t\tthrow new UnsupportedOperationException();");
    }
}
