/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

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
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils.assertEqualFiles;
import static org.junit.Assert.assertTrue;

/**
 * Test verifies that code generated by the generator is equal to the code mentioned in the thesis.
 * User: Jan Hýbl
 * Date: 29.7.12
 * Time: 19:35
 */
public class Fields {

    private static API api;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Instantiator fields test");
        scannerConfiguration.setPath("testres/text/instantiator/fields");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("fields");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of the test
        TestUtils.delete(new File("output/tests/text/instantiator/fields"));
    }

    @Test
    public void testFields() {

        Generator generator = new InstantiatorGenerator(new GeneratorConfiguration());
        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("fields");
        job.setOutputDir("output/tests/text/instantiator/fields");
        job.setOutputPackage("test.%s");
        generator.generate(api, job);

        File resultFile = new File("output/tests/text/instantiator/fields/test/lib/TestedClassInstantiator.java");
        assertTrue(resultFile.exists());

        File expected = new File("testres/text/instantiator/fields_exp/test/lib/TestedClassInstantiator.java");

        assertEqualFiles(expected, resultFile);

    }
}
