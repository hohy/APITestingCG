package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.*;
import cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.InstantiatorGenerator;
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

import static cz.cvut.fit.hybljan2.apitestingcg.generator.Generator.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 11.2.12
 * Time: 19:57
 */
public class InstantiatorGeneratorTest {
    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Instantiator test");
        scannerConfiguration.setPath("testres/instantiator");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("instantiator");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of tests
        TestUtils.delete(new File("output/tests/instantiator"));
    }

    /**
     * Test of generating simple constructor for extender class
     */
    @Test
    public void TestConstructors() {

        cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.ClassA.ClassA");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/ClassAInstantiator.java");
        assertTrue(resultFile.exists());

        //File expected = new File("testres/extender_exp/ClassAExtender.java");

        //FileAssert.assertEquals(expected, resultFile);

    }

    /**
     * Test of generating simple constructor for extender class
     */
    @Test
    public void TestConstructors2() {

        cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Constructors");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/ConstructorsInstantiator.java");
        assertTrue(resultFile.exists());

        //File expected = new File("testres/extender_exp/ClassAExtender.java");

        //FileAssert.assertEquals(expected, resultFile);

    }
    
    @Test
    public void testSimplifyName() {
        assertEquals("List", cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.Generator.simplifyName("java.util.List"));
        assertEquals("Set", cz.cvut.fit.hybljan2.apitestingcg.cmgenerator.Generator.simplifyName("Set"));
    }

}
