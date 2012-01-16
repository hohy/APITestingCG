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

    public GeneratorDirector(GeneratorConfiguration generatorConfiguration) {
        instGen.setConfiguration(generatorConfiguration);
        extGen.setConfiguration(generatorConfiguration);
    }

    public void generate(API api, GeneratorJobConfiguration jobConfiguration) {
        //instGen.generate(api, jobConfiguration);
        // get all packages in api
        for(APIPackage pkg : api.getPackages()) {
            // get all classes from every package
            for(APIClass cls : pkg.getClasses()) {
                // filter out all abstract classes
                if(!cls.getModifiers().contains(APIModifier.Modifier.ABSTRACT)) {
                    instGen.generate(cls, jobConfiguration);
                }
            }
        }

        //extGen.generate(api, jobConfiguration);
        for(APIPackage pkg : api.getPackages()) {
            for(APIClass cls : pkg.getClasses()) {
                // filter out final classes and annotations
                if(!cls.getModifiers().contains(APIModifier.Modifier.FINAL) && !cls.getType().equals(APIItem.Kind.ANNOTATION)) {
                    extGen.generate(cls, jobConfiguration);
                }
            }
        }
    }
}
