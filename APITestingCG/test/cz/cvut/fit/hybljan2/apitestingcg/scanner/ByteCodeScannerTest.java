/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import java.io.File;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.ScannerConfiguration;
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
     * Test of setConfiguration method, of class ByteCodeScanner.
     */
    @Test
    public void testSetConfiguration() {
        System.out.println("setConfiguration");
        ScannerConfiguration sc = null;
        ByteCodeScanner instance = new ByteCodeScanner();
        instance.setConfiguration(sc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scan method, of class ByteCodeScanner.
     */
    @Test
    public void testScan() {
        System.out.println("scan");
        ByteCodeScanner instance = new ByteCodeScanner();
        instance.setConfiguration(testLibCfg);        
        String expResult = TestUtils.readFileToString("testres/testScannerLib.string");
        API result = instance.scan();
        String strresult = result.toString();
        System.out.println("Expected:\n" + expResult);
        System.out.println("Result\n" + strresult);
        assertEquals(expResult, strresult);
    }
}
