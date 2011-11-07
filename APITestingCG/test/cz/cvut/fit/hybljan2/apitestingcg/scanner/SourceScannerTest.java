/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import java.util.List;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import java.io.File;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hohy
 */
public class SourceScannerTest {
    
    private static ScannerConfiguration testLibCfg;
    
    public SourceScannerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        testLibCfg = new ScannerConfiguration();
        testLibCfg.setApiName("Test APIScanner Library");
        testLibCfg.setApiVersion("0.1");
        testLibCfg.setClasspath("");
        testLibCfg.setPath("testres" + File.separator + "testScannerLib/");
        testLibCfg.setSource(ScannerConfiguration.APISource.SOURCECODE);
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
     * Test of setConfiguration method, of class SourceScanner.
     */
    @Test
    public void testSetConfiguration() {
        System.out.println("setConfiguration");
        ScannerConfiguration sc = null;
        SourceScanner instance = new SourceScanner();
        instance.setConfiguration(sc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scan method, of class SourceScanner.
     */
    @Test
    public void testScan() {
        System.out.println("scan");
        SourceScanner instance = new SourceScanner();
        instance.setConfiguration(testLibCfg);
        String expResult = TestUtils.readFileToString("testres/testScannerLib.string");
        API result = instance.scan();
        System.out.println("Expected:\n" + expResult); 
        System.out.println("\n\nResult: \n" + result.toString());
        assertEquals(expResult, result.toString());
    }
}
