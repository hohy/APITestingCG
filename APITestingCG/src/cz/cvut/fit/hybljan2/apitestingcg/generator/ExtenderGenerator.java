package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem.Kind;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;

/**
 *
 * @author Jan Hybl
 */
public class ExtenderGenerator extends ClassGenerator {

    @Override
    public void generate(APIClass cls, GeneratorJobConfiguration jobConfiguration) {

        addImport(cls.getFullName());

        // import all package of class...   TODO: remove this
        addImport(cls.getPackageName() + ".*");

        // set package name
        setPackageName(generateName(jobConfiguration.getOutputPackage(), cls.getPackageName()));

        String pattern = null;
        // if tested item is interface, create Implementator, otherwise Extender
        if(cls.getType() == Kind.INTERFACE) {
            pattern = configuration.getImplementerClassIdentifier();
            addImplemening(cls.getName());
        } else {
            pattern = configuration.getExtenderClassIdentifier();
            setExtending(cls.getName());
        }

        setName(generateName(pattern, cls.getName()));

        // constructors tests
        for(APIMethod constructor : cls.getConstructors()) {
            MethodGenerator cnstr = new ExtenderConstructorGenerator(getName(), constructor);
            addConstructor(cnstr);
        }

        // method overriding tests
        for(APIMethod method : cls.getMethods()) {
            // filter out static and final methods - they can't be overridden
            if(! (method.getModifiers().contains(Modifier.STATIC) || method.getModifiers().contains(Modifier.FINAL))) {
                MethodGenerator mgen = new OverriderMethodGenerator(method);
                addMethod(mgen);
            }
        }

        // protected field tests
        for(APIField field : cls.getFields()) {
//                        if(field.getModifiers().contains(Modifier.PROTECTED)) {
            MethodGenerator ftmg = new FieldTestMehtodGenerator(cls, field, field.getName(), configuration);
            addMethod(ftmg);
//                        }
        }
        generateClassFile();
    }

}
