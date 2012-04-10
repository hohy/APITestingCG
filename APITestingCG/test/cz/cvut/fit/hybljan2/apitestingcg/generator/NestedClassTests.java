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
 * Date: 9.4.12
 * Time: 13:53
 */

public class NestedClassTests {

    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Nested classes test");
        scannerConfiguration.setPath("testres/nested");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("nested");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of tests
        TestUtils.delete(new File("output/tests/nested"));
    }

    @Test
    public void TestMethods() {

        cz.cvut.fit.hybljan2.apitestingcg.generator.Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("nested");
        job.setOutputDir("output/tests/nested");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);

        File resultFile = new File("output/tests/nested/test/lib/NestedInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/nested_exp/lib/NestedInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);

    }

}
