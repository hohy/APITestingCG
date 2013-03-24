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
 * Date: 23.12.12
 * Time: 16:30
 */
public class GenericAncestors {

    private static API api;

    Generator egenerator = new ExtenderGenerator(new GeneratorConfiguration());

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Generics anc test");
        scannerConfiguration.setPath("testres/genancest");
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("generics");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();

        // delete output files from previous run of tests
        TestUtils.delete(new File("output/tests/genancest"));
    }

    @Test
    public void TestGenerics() {

        GeneratorJobConfiguration job = new GeneratorJobConfiguration();
        job.setApiId("annotations");
        job.setOutputDir("output/tests/genancest");
        job.setOutputPackage("test.%s");
        job.addWhitelistRule(new WhitelistRule("lib.GenericAncestor"));
        egenerator.generate(api, job);

        File resultFile = new File("output/tests/genancest/test/lib/GenericAncestorExtender.java");
        assertTrue(resultFile.exists());
        File expected = new File("testres/genancest_exp/GenericAncestorExtender.java");
        assertEqualFiles(expected, resultFile);

    }

}
