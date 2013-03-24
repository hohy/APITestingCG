/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package text.generatedcode.extender;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.generator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.generator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.generator.InstantiatorGenerator;
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
 * Date: 17.10.12
 * Time: 16:47
 */
public class Generic {
    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Generic test");
        scannerConfiguration.setPath("testres/text/extender/generics");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("enum");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of the test
        TestUtils.delete(new File("output/tests/text/extender/generics"));
    }

    @Test
    public void testGenericsExtender() {

        Generator generator = new ExtenderGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("enum");
        job.setOutputDir("output/tests/text/extender/generics");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);

        File resultFile = new File("output/tests/text/extender/generics/test/lib/GenericExtender.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/text/extender/generics_exp/test/lib/GenericExtender.java");

        assertEqualFiles(expected, resultFile);

    }

    @Test
    public void testGenericsInstantiator() {

        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("enum");
        job.setOutputDir("output/tests/text/extender/generics");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);

        File resultFile = new File("output/tests/text/extender/generics/test/lib/GenericInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/text/extender/generics_exp/test/lib/GenericInstantiator.java");

        assertEqualFiles(expected, resultFile);

    }
}
