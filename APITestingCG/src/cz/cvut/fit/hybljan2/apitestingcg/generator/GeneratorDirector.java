package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
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
        instGen.generate(api, this);
        extGen.generate(api, this);
    }
}
