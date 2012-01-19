package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

/**
 * Generates method to test Enum fields.
 *
 * Example: (for Enum Planet)
 *
 * public Planet createEARTH() {
 *      return Planet.EARTH;
 * }
 *
 * User: Jan Hýbl
 * Date: 19.1.12
 * Time: 12:09
 */
public class EnumConstructorMethodGenerator extends MethodGenerator {
    
    public EnumConstructorMethodGenerator(String name, APIField fld, GeneratorConfiguration configuration) {
        this.configuration = configuration;
        setName(generateName(configuration.getCreateInstanceIdentifier(), fld.getName()));  // TODO: add own identifier item into configuration
        setReturnType(name);
        setModifiers("public");
        StringBuilder mthBody = new StringBuilder("\t\t");
        mthBody.append("return ").append(name).append('.').append(fld.getName()).append(";");
        setBody(mthBody.toString());
    }
    
}
