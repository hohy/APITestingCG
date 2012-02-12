package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.EnumGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;
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

    /**
     * Test of generating simple constructor for extender class
     */
    @Test
    public void TestPlanets() {

        Generator generator = new EnumGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("enum");
        job.setOutputDir("output/tests/enum");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Planet");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/extender/test/lib/PlanetInstantiator.java");
        assertTrue(resultFile.exists());

        //File expected = new File("testres/extender_exp/ClassAExtender.java");

        //FileAssert.assertEquals(expected, resultFile);

    }
}
