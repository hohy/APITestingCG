/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.scanner;

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
    
    public ByteCodeScannerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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
        API expResult = null;
        API result = instance.scan();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
