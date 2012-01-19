package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 16.1.12
 * Time: 16:58
 */
public class EnumGenerator extends ClassGenerator {
    @Override
    public void generate(APIClass cls, GeneratorJobConfiguration jobConfiguration) {

        // set name of package for generated class - read new name pattern from configuration and add original package..
        setPackageName(generateName(jobConfiguration.getOutputPackage(), cls.getPackageName()));

        // new class has to import original enumeration
        addImport(cls.getFullName());

        setName(generateName("%sEnumTest", cls.getName())); // TODO: add item into configuration

        // for all of enum fields generate method that tests this field
        for(APIField fld : cls.getFields()) {

            if(fld.getVarType().equals(cls.getName())) { // test if field is enum field or just variable
                /*
                 * generate method like this:
                 * public EnumClass createFIELD() {
                 *     return EnumClass.FIELD();
                 * }
                 */
                MethodGenerator mthGen = new MethodGenerator();
                mthGen.setName(generateName(configuration.getCreateInstanceIdentifier(), fld.getName()));  // TODO: add own identifier item into configuration
                mthGen.setReturnType(cls.getName());
                mthGen.setModifiers("public");
                StringBuilder mthBody = new StringBuilder("\t\t");
                mthBody.append("return ").append(cls.getName()).append('.').append(fld.getName()).append(";");
                mthGen.setBody(mthBody.toString());
                addConstructor(mthGen);

            } else {  // it's not a enum field but constant or variable. Test it in same way as in Instantiator or Extender.
                MethodGenerator fldGen = new FieldTestMehtodGenerator(cls, fld, cls.getName() + "." + fld.getName(), configuration);
                addMethod(fldGen);
            }

        }

        for(APIMethod mth : cls.getMethods()) {
            MethodGenerator mthGen = new MethodCallerMethodGenerator(mth, cls, configuration);
            addMethod(mthGen);
        }

        generateClassFile();

    }
}
