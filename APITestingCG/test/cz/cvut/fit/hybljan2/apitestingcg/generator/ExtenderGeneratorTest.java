package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.ngenerator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.ngenerator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 6.2.12
 * Time: 9:44
 */
public class ExtenderGeneratorTest {

    private API api;

    @BeforeClass
    public void Setup() {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Extender test");
        scannerConfiguration.setPath("testres/generator/extender");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("extender");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

       api = scanner.scan();
    }

    /**
     * Test of generating simple constructor for extender class
     */
    @Test
    public void TestConstructors() {

        Generator generator = new ExtenderGenerator();
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("extender");
        job.setOutputDir("output/tests/extender");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);
    }
}
