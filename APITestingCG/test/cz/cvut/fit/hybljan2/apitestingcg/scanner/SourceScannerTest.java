/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import org.junit.*;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
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
        testLibCfg.setApiVersion("v0.1");
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
