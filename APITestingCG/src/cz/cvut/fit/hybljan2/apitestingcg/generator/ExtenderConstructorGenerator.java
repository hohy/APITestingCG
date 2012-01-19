package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 19.1.12
 * Time: 10:48
 */
public class ExtenderConstructorGenerator extends MethodGenerator {


    public ExtenderConstructorGenerator(String name, APIMethod constructor) {
        setModifiers("public");
        setName(name);

        setParams(getMethodParamList(constructor));

        StringBuilder sb = new StringBuilder();

        sb.append("\t\tsuper(").append(getMethodParamNameList(getParams())).append(");");

        setBody(sb.toString());
    }

}
