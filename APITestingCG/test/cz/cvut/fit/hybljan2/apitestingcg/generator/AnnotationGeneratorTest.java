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

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.4.12
 * Time: 22:43
 */
public class AnnotationGeneratorTest {

    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Annotations test");
        scannerConfiguration.setPath("testres/annotations");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("annotations");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of tests
        TestUtils.delete(new File("output/tests/annotations"));
    }

    @Test
    public void TestPackage() {

        Generator generator = new AnnotationGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/annotations/package");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.ALL);
        r.setRule("lib.PackageAnnotation");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/annotations/package/test/lib/packageannotation/package-info.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/annotations_exp/test/lib/packageannotation/package-info.java");

        FileAssert.assertEquals(expected, resultFile);

    }

    @Test
    public void TestAnnotation() {

        Generator generator = new AnnotationGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/annotations/annotation");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.ALL);
        r.setRule("lib.AnnotationAnnotation");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/annotations/annotation/test/lib/AnnotationAnnotationAnnotation.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/annotations_exp/test/lib/AnnotationAnnotationAnnotation.java");

        FileAssert.assertEquals(expected, resultFile);
    }


    @Test
    public void TestField() {
        Generator generator = new AnnotationGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/annotations/field");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.ALL);
        r.setRule("lib.FieldAnnotation");
        job.addWhitelistRule(r);
        generator.generate(api, job);

        File resultFile = new File("output/tests/annotations/field/test/lib/FieldAnnotationClass.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/annotations_exp/test/lib/FieldAnnotationClass.java");

        FileAssert.assertEquals(expected, resultFile);
    }

    @Test
    public void AllTargetsAnnotationTest() {
        Generator generator = new AnnotationGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/annotations/allTargets");
        job.setOutputPackage("test.%s");
        WhitelistRule r = new WhitelistRule();
        r.setItem(WhitelistRule.RuleItem.ALL);
        r.setRule("lib.PackageAnnotation");
        WhitelistRule r2 = new WhitelistRule();
        r2.setItem(WhitelistRule.RuleItem.ALL);
        r2.setRule("lib.AllTargetsAnnotation");
        job.addWhitelistRule(r2);
        generator.generate(api, job);

        //class
        File resultFile = new File("output/tests/annotations/allTargets/test/lib/AllTargetsAnnotationClass.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/annotations_exp/test/lib/AllTargetsAnnotationClass.java");
        FileAssert.assertEquals(expected, resultFile);

        //class default value
        resultFile = new File("output/tests/annotations/allTargets/test/lib/AllTargetsAnnotationClassDV.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/annotations_exp/test/lib/AllTargetsAnnotationClassDV.java");
        FileAssert.assertEquals(expected, resultFile);

        //annotation
        resultFile = new File("output/tests/annotations/allTargets/test/lib/AllTargetsAnnotationAnnotation.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/annotations_exp/test/lib/AllTargetsAnnotationAnnotation.java");
        FileAssert.assertEquals(expected, resultFile);

        //annotation default value
        resultFile = new File("output/tests/annotations/allTargets/test/lib/AllTargetsAnnotationAnnotationDV.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/annotations_exp/test/lib/AllTargetsAnnotationAnnotationDV.java");
        FileAssert.assertEquals(expected, resultFile);

        //package
        resultFile = new File("output/tests/annotations/allTargets/test/lib/alltargetsannotation/package-info.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/annotations_exp/test/lib/package-info.java");
        FileAssert.assertEquals(expected, resultFile);

        //package default value
        resultFile = new File("output/tests/annotations/allTargets/test/lib/alltargetsannotationDV/package-info.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/annotations_exp/test/lib/package-info-dv.java");
        FileAssert.assertEquals(expected, resultFile);



    }

}
