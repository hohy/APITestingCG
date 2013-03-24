/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils.assertEqualFiles;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 11.4.12
 * Time: 13:13
 */
public class GenericsTypesTest {


    private static API api;

    Generator igenerator = new InstantiatorGenerator(new GeneratorConfiguration());
    Generator egenerator = new ExtenderGenerator(new GeneratorConfiguration());

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

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic01"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);

        File resultFile = new File("output/tests/generics/test/lib/Generic01Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic01Instantiator.java");
        assertEqualFiles(expected, resultFile);

        resultFile = new File("output/tests/generics/test/lib/Generic01Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic01Extender.java");
        assertEqualFiles(expected, resultFile);
        
    }

    @Test
    public void TestGenerics2() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic2"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);
        
        File resultFile = new File("output/tests/generics/test/lib/Generic2Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic2Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic2Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic2Extender.java");
        assertEqualFiles(expected, resultFile);
        
    }

    @Test
    public void TestGenerics3() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic3"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic3Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic3Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic3Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic3Extender.java");
        assertEqualFiles(expected, resultFile);
    }


    @Test
    public void TestGenerics4() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic4"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic4Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic4Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic4Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic4Extender.java");
        assertEqualFiles(expected, resultFile);
        
    }

    @Test
    public void TestGenerics10() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic10"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic10Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic10Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic10Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic10Extender.java");
        assertEqualFiles(expected, resultFile);

    }

    @Test
    public void TestGenerics12() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic12"));
        egenerator.generate(api, job);
        igenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic12Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic12Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic12Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic12Extender.java");
        assertEqualFiles(expected, resultFile);

    }

    @Test
    public void TestGenerics17() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic17"));
        egenerator.generate(api, job);
        igenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic17Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic17Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic17Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic17Extender.java");
        assertEqualFiles(expected, resultFile);

    }


    @Test
    public void TestGenerics5() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic5"));
        egenerator.generate(api, job);
        igenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic5Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic5Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic5Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic5Extender.java");
        assertEqualFiles(expected, resultFile);

    }

    @Test
    public void TestGenerics8() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic8"));
        egenerator.generate(api, job);
        igenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic8Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic8Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic8Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic8Extender.java");
        assertEqualFiles(expected, resultFile);

    }

    @Test
    public void TestGenerics13() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.Generic13"));
        egenerator.generate(api, job);
        igenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/Generic13Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/Generic13Instantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/Generic13Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/Generic13Extender.java");
        assertEqualFiles(expected, resultFile);
    }

    @Test
    public void TestTypeParmsOrder() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.TypeParams"));
        egenerator.generate(api, job);
        igenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/TypeParamsInstantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/TypeParamsInstantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/TypeParamsExtender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/TypeParamsExtender.java");
        assertEqualFiles(expected, resultFile);

    }

    @Test
    public void TestGenericsMethod1() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.GenericMethod1"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);

        File resultFile = new File("output/tests/generics/test/lib/GenericMethod1Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/GenericMethod1Instantiator.java");
        assertEqualFiles(expected, resultFile);

        resultFile = new File("output/tests/generics/test/lib/GenericMethod1Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/GenericMethod1Extender.java");
        assertEqualFiles(expected, resultFile);
        
    }


    @Test
    public void TestGenericsMethod2() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.GenericMethod2"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);

        File resultFile = new File("output/tests/generics/test/lib/GenericMethod2Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/GenericMethod2Instantiator.java");
        assertEqualFiles(expected, resultFile);

        resultFile = new File("output/tests/generics/test/lib/GenericMethod2Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/GenericMethod2Extender.java");
        assertEqualFiles(expected, resultFile);
    }

    @Test
    public void TestGenericsConstructor1() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.GenericConstructor1"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);

        File resultFile = new File("output/tests/generics/test/lib/GenericConstructor1Instantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/GenericConstructor1Instantiator.java");
        assertEqualFiles(expected, resultFile);

        resultFile = new File("output/tests/generics/test/lib/GenericConstructor1Extender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/GenericConstructor1Extender.java");
        assertEqualFiles(expected, resultFile);
    }

    @Test
    public void TestGenericNames() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/generics");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.GenericNames"));
        igenerator.generate(api, job);
        egenerator.generate(api, job);
        File resultFile = new File("output/tests/generics/test/lib/GenericNamesInstantiator.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/generics_exp/test/lib/GenericNamesInstantiator.java");
        assertEqualFiles(expected, resultFile);


        resultFile = new File("output/tests/generics/test/lib/GenericNamesExtender.java");
        assertTrue(resultFile.exists());
        expected = new File("testres/generics_exp/test/lib/GenericNamesExtender.java");
        assertEqualFiles(expected, resultFile);
    }


}
