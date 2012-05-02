/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import org.junit.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author hohy
 */
public class SourceScannerTest {

    private static ScannerConfiguration testLibCfg;
    private static API api;

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
        SourceScanner instance = new SourceScanner();
        instance.setConfiguration(testLibCfg);
        String expResult = TestUtils.readFileToString("testres/testScannerLib.string");
        API result = instance.scan();
        System.out.println("Expected:\n" + expResult);
        System.out.println("\n\nResult: \n" + result.toString());
        assertEquals(expResult, result.toString());
    }

    @Test
    public void testFieldsScan() throws IOException, ClassNotFoundException {
        final String TEST_FOLDER = "testres/scanner_src_fields";

        ScannerConfiguration scannerConfiguration = new ScannerConfiguration();
        scannerConfiguration.setApiName("Src scanner fields test");
        scannerConfiguration.setPath(TEST_FOLDER);
        scannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        scannerConfiguration.setSourceVersion("1.7");
        scannerConfiguration.setId("src_scanner_fields");

        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(scannerConfiguration);

        api = scanner.scan();
        
        String expected = TestUtils.readFileToString(TEST_FOLDER + "/lib/Fields1.string");
        APIClass fieldsClass = api.findClass("lib.Fields1");
        String result = fieldsClass.toString();

        assertEquals(expected, result);

        for(APIField field : fieldsClass.getFields()) {
            if(field.getName().equals("g1")) {
                assertEquals("java.util.List", field.getVarType().getName());
                assertEquals("java.lang.String", field.getVarType().getTypeArgs().get(0).getName());
            }

            if(field.getName().equals("g2")) {
                assertEquals("java.util.List", field.getVarType().getName());
                assertEquals("java.util.Map", field.getVarType().getTypeArgs().get(0).getName());
                assertEquals("java.lang.Integer", field.getVarType().getTypeArgs().get(0).getTypeArgs().get(0).getName());
                assertEquals("java.util.List", field.getVarType().getTypeArgs().get(0).getTypeArgs().get(1).getName());
                assertEquals("java.util.List", field.getVarType().getTypeArgs().get(0).getTypeArgs().get(1).getTypeArgs().get(0).getName());
            }
        }
    }
}
