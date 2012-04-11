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
 * Date: 11.4.12
 * Time: 13:13
 */
public class GenericsTypesTest {


    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Generics test");
        scannerConfiguration.setPath("testres/generics");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("generics");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of tests
        TestUtils.delete(new File("output/tests/generics"));
    }

    @Test
    public void TestGenerics() {

        Generator igenerator = new InstantiatorGenerator(new GeneratorConfiguration());
        Generator egenerator = new ExtenderGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
//        WhitelistRule r = new WhitelistRule();
//        r.setItem(WhitelistRule.RuleItem.ALL);
//        r.setRule("lib.PackageAnnotation");
//        job.addWhitelistRule(r);
        igenerator.generate(api, job);
        egenerator.generate(api, job);

        File resultFile = new File("output/tests/generics/test/lib/Generics1Instantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/generics_exp/test/lib/Generics1Instantiator.java");

        FileAssert.assertEquals(expected, resultFile);

    }
}
