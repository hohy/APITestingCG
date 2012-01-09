package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;

/**
 * Class directs a process of generating of test code.
 * User: Jan Hýbl
 * Date: 9.1.12
 * Time: 14:30
 */
public class GeneratorDirector {

    private InstantiatorGenerator instGen = new InstantiatorGenerator();
    private ExtenderGenerator extGen = new ExtenderGenerator();

    public void generate(API api) {
        instGen.generate(api);
        extGen.generate(api);
    }
}
