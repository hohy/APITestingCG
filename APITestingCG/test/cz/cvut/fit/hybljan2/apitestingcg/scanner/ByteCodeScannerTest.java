/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Developmen and Distribution License (CDDL). You can obtain a copy of the CDDL at http://www.netbeans.org/cddl.html.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethodParameter;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIType;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import org.junit.*;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Jan Hybl
 */
public class ByteCodeScannerTest {
    private static ScannerConfiguration testLibCfg;

    public ByteCodeScannerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        testLibCfg = new ScannerConfiguration();
        testLibCfg.setApiName("Test APIScanner Library");
        testLibCfg.setApiVersion("v0.1");
        testLibCfg.setClasspath("");
        testLibCfg.setPath("testres" + File.separator + "testScannerLib.jar");
        testLibCfg.setSource(ScannerConfiguration.APISource.BYTECODE);
        testLibCfg.setSourceVersion("1.7");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of scan method, of class ByteCodeScanner.
     */
    @Test
    public void testScan() {
        System.out.println("scan");
        ByteCodeScanner instance = new ByteCodeScanner();
        instance.setConfiguration(testLibCfg);
        String expResult = TestUtils.readFileToString("testres/testScannerLib.bcstring");
        API result = instance.scan();
        String strresult = result.toString();
        System.out.println("Expected:\n" + expResult);
        System.out.println("Result\n" + strresult);
        assertEquals(expResult, strresult);
    }

    @Test
    public void testBytocodeGenerics() throws ClassNotFoundException {
        testLibCfg = new ScannerConfiguration();
        testLibCfg.setApiName("Test APIScanner Library");
        testLibCfg.setApiVersion("v0.1");
        testLibCfg.setClasspath("");
        testLibCfg.setPath("testres" + File.separator + "Generic.jar");
        testLibCfg.setSource(ScannerConfiguration.APISource.BYTECODE);
        testLibCfg.setSourceVersion("1.7");
        ByteCodeScanner instance = new ByteCodeScanner();
        instance.setConfiguration(testLibCfg);
        API result = instance.scan();
        APIClass cls = result.findClass("lib.Generic");
        List<APIMethodParameter> parameterList = cls.getMethods().iterator().next().getParameters();
        assertEquals("java.util.List",parameterList.get(0).getType().getName());
        assertEquals("?",parameterList.get(0).getType().getTypeArgs().get(0).getName());
        assertEquals(APIType.BoundType.UPPER,parameterList.get(0).getType().getTypeArgs().get(0).getBound());
        assertEquals("java.util.List",parameterList.get(0).getType().getTypeArgs().get(0).getTypeArgs().get(0).getName());
        assertEquals("S",parameterList.get(0).getType().getTypeArgs().get(0).getTypeArgs().get(0).getTypeArgs().get(0).getName());

        assertEquals("java.util.List",parameterList.get(1).getType().getName());
        assertEquals("?",parameterList.get(1).getType().getTypeArgs().get(0).getName());
        assertEquals(APIType.BoundType.LOWER,parameterList.get(1).getType().getTypeArgs().get(0).getBound());
        assertEquals("S",parameterList.get(1).getType().getTypeArgs().get(0).getTypeArgs().get(0).getName());
    }
}
