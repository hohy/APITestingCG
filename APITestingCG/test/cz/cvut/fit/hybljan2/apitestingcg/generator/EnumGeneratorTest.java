package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import junitx.framework.FileAssert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 12.2.12
 * Time: 23:01
 */
public class EnumGeneratorTest {

    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Enum test");
        scannerConfiguration.setPath("testres/enum");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("enum");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of whiteListTest2
        TestUtils.delete(new File("output/tests/enum"));
    }

    @Test
    public void TestPlanets() {

        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("enum");
        job.setOutputDir("output/tests/enum");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);

        File resultFile = new File("output/tests/enum/test/lib/PlanetInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/enum_exp/test/lib/PlanetInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);

    }

    /**
     * I want to figure out, if i need specialized generator for enum types, 
     * or i can use standard instantiator generator. It doesn't assert anything,
     * it's only simple check. First condition in the InstantiatorGenerator
     * has to be commented.
     */
    @Test
    public void TestInstantiator() {
        GeneratorConfiguration gc = new GeneratorConfiguration();
        gc.setInstantiatorClassIdentifier("Test%sInstantiator");
        Generator generator = new InstantiatorGenerator(gc);
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("enum");
        job.setOutputDir("output/tests/enum");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);
    }
}
