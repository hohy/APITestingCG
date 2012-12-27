package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
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

        cz.cvut.fit.hybljan2.apitestingcg.generator.Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
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

        File expected = new File("testres/instantiator_exp/ClassAInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);

    }

    /**
     * Test of generating simple constructor for extender class
     */
    @Test
    public void TestConstructors2() {

        cz.cvut.fit.hybljan2.apitestingcg.generator.Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
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

        File expected = new File("testres/instantiator_exp/ConstructorsInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);

    }

    @Test
    public void testSimplifyName() {
        assertEquals("List", cz.cvut.fit.hybljan2.apitestingcg.generator.Generator.simplifyName("java.util.List"));
        assertEquals("Set", cz.cvut.fit.hybljan2.apitestingcg.generator.Generator.simplifyName("Set"));
    }

    @Test
    public void TestMethods() {

        cz.cvut.fit.hybljan2.apitestingcg.generator.Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.ALL);
        r.setRule("lib.Methods");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/MethodsInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/MethodsInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);

    }

    @Test
    public void TestFields1() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Fields1");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/Fields1Instantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/Fields1Instantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestFields2() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Fields2");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/Fields2Instantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/Fields2Instantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestFields3() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Fields3");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/Fields3Instantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/Fields3Instantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestFields4() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Fields4");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/Fields4Instantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/Fields4Instantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    /**
     * Test of the instantiator generator that check if the generated code
     * reflects that the tested class extends some class.
     */
    @Test
    public void TestExtending() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.ExtendingClass1");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/ExtendingClass1Instantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/ExtendingClass1Instantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestAncestors() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Ancestors");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/AncestorsInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/AncestorsInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestExceptionNameConflictHandling() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.ExceptionName");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/ExceptionNameInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/instantiator_exp/ExceptionNameInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestExceptions() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Exceptions");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/ExceptionsInstantiator.java");

        File expected = new File("testres/instantiator_exp/ExceptionsInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestArrays() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Arrays");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/ArraysInstantiator.java");

        File expected = new File("testres/instantiator_exp/ArraysInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void TestGenerics() {
        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("instantiator");
        job.setOutputDir("output/tests/instantiator");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.INSTANTIATOR);
        r.setRule("lib.Generics");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/instantiator/test/lib/GenericsInstantiator.java");

        File expected = new File("testres/instantiator_exp/GenericsInstantiator.java");

        FileAssert.assertEquals(expected, resultFile);
    }
}
