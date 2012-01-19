package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

import java.util.List;

/**
 * Class generates test of constructors in Instantiators. There can be 2 variants of this test.
 *
 * First variant:
 * public ClassA createClassA(List a) {
 *      return new ClassA(a);
 * }
 *
 * Second variant (with null parameters):
 * public ClassA createClassA(List a) {
 *      return new ClassA(null);
 * }
 *
 *
 * User: Jan Hýbl
 * Date: 19.1.12
 * Time: 10:56
 */
public class InstantiatorConstructorGenerator extends MethodGenerator {

    /**
     * Constructor for first variant of the test.
     * @param name  name of class, that tested constuctor creates (mostly it's equal to constuctor.getName(),
     *              but sometimes it's diffrent - when constructor create instance of superclass or some interface)
     * @param constructor   tested constuctor.
     * @param configuration generator configuration (define names,...)
     */
    public InstantiatorConstructorGenerator(String name, APIMethod constructor, GeneratorConfiguration configuration) {
        this.configuration = configuration;
        setModifiers("public");
        setName(generateName(configuration.getCreateInstanceIdentifier(), name));
        setReturnType(name);
        List<String[]> params = getMethodParamList(constructor);
        setParams(params);
        setBody(generateConstructorBody(name, getMethodParamNameList(params)));
    }

    /**
     * Constuctor for second variant of the test.
     * @param name  name of class, that tested constuctor creates.
     * @param constructor   tested constructor
     * @param nullParamString   This string is used as parameters in tested constructor.
     * @param configuration generator configuration (define names,...)
     */
    public InstantiatorConstructorGenerator(String name, APIMethod constructor, String nullParamString, GeneratorConfiguration configuration) {
        this.configuration = configuration;
        setModifiers("public");
        setName(generateName(configuration.getCreateInstanceIdentifier(), name));
        setReturnType(name);
        List<String[]> params = getMethodParamList(constructor);
        setParams(params);
        setBody(generateConstructorBody(name, nullParamString));
    }

    private String generateConstructorBody(String clsName, String paramsString) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\treturn new ").append(clsName).append("(");
        sb.append(paramsString);
        sb.append(");\n");
        return sb.toString();
    }
}
