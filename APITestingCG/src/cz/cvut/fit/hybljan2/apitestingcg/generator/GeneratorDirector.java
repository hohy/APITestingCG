package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;

/**
 * Class directs a process of generating of test code.
 * User: Jan Hýbl
 * Date: 9.1.12
 * Time: 14:30
 */
public class GeneratorDirector {

    // configuration
    private GeneratorConfiguration configuration;

    // generators
    private InstantiatorGenerator instGen = new InstantiatorGenerator();
    private ExtenderGenerator extGen = new ExtenderGenerator();
    private EnumGenerator enumGen = new EnumGenerator();

    public GeneratorDirector(GeneratorConfiguration generatorConfiguration) {
        instGen.setConfiguration(generatorConfiguration);
        extGen.setConfiguration(generatorConfiguration);
        enumGen.setConfiguration(generatorConfiguration);
    }

    public void generate(API api, GeneratorJobConfiguration jobConfiguration) {

        // get all packages in api
        for(APIPackage pkg : api.getPackages()) {
            // get all classes from every package
            for(APIClass cls : pkg.getClasses()) {

                switch (cls.getType()) {    // for every kind of APIItem generate relevant files.
                    case CLASS:
                    case INTERFACE:
                        // filter out all abstract classes
                        if(!cls.getModifiers().contains(APIModifier.Modifier.ABSTRACT)) {
                            instGen.generate(cls, jobConfiguration);
                        }
                        // filter out final classes and annotations
                        if(!cls.getModifiers().contains(APIModifier.Modifier.FINAL)) {
                            extGen.generate(cls, jobConfiguration);
                        }
                        break;
                    case ENUM:
                        enumGen.generate(cls, jobConfiguration);
                        break;
                    case ANNOTATION:
                        // TODO: add annotation test generator
                }


            }
        }
    }
}
