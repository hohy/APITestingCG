package text.generatedcode.instantiator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.generator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.generator.InstantiatorGenerator;
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
 * Test verifies that code generated by the generator is equal to the code mentioned in the thesis.
 * User: Jan Hýbl
 * Date: 29.7.12
 * Time: 19:35
 */
public class Enums {

    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Enum test");
        scannerConfiguration.setPath("testres/text/instantiator/enum");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("enum");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of the test
        TestUtils.delete(new File("output/tests/text/instantiator/enum"));
    }

    @Test
    public void testEnum() {

        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("enum");
        job.setOutputDir("output/tests/text/instantiator/enum");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);

        File resultFile = new File("output/tests/text/instantiator/enum/test/lib/EnumTypeInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/text/instantiator/enum_exp/test/lib/EnumTypeInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);

    }
}
