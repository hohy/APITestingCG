package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.*;
import cz.cvut.fit.hybljan2.apitestingcg.ngenerator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.ngenerator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import junitx.framework.FileAssert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 6.2.12
 * Time: 9:44
 */
public class ExtenderGeneratorTest {

    private static API api;

    @BeforeClass
    public static void Setup() {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Extender test");
        scannerConfiguration.setPath("testres/extender");
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

        Generator generator = new ExtenderGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("extender");
        job.setOutputDir("output/tests/extender");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.EXTENDER);
        r.setRule("lib.ClassA.ClassA");
        job.addWhitelistRule(r);
        generator.generate(api, job);
        
        File resultFile = new File("output/tests/extender/test/lib/ClassAExtender.java");
        assertTrue(resultFile.exists());
        
        File expected = new File("testres/extender_exp/ClassAExtender.java");

        FileAssert.assertEquals(expected, resultFile);

    }
}
