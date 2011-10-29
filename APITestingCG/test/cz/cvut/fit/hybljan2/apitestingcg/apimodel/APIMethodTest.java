package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan Hybl
 */
public class APIMethodTest {
    
    private static APIMethod[] testInstaces = new APIMethod[5];
    
    public APIMethodTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SourceScanner sc = new SourceScanner("testres/testAPIMethodRes/", "", "1.7");
        API api = sc.scan();
        api.getPackages().get(0).getClasses().get(0).getMethods().toArray(testInstaces);
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
     * Test of toString method, of class APIMethod.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String[] expResults = {
                "public method void methodA()",
                "public static final method int methodB(int,int)",
                "protected method java.io.File methodC(java.util.List,float,java.util.Queue)",
                "public method void methodD() throws java.io.IOException java.lang.Exception",
            };
        for (int i = 0; i < expResults.length; i++) {
            String expResult = expResults[i];
            String result = testInstaces[i].toString();
            assertEquals(expResult, result);      
        }        
    }

    /**
     * Test of getParameters method, of class APIMethod.
     */
    @Test
    public void testGetParameters() {
        System.out.println("getParameters");
        APIMethod instance = testInstaces[2];
        List expResult = new LinkedList();
        expResult.add("java.util.List");
        expResult.add("float");
        expResult.add("java.util.Queue");
        List result = instance.getParameters();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReturnType method, of class APIMethod.
     */
    @Test
    public void testGetReturnType() {
        System.out.println("getReturnType");
        APIMethod instance = testInstaces[1];
        String expResult = "int";
        String result = instance.getReturnType();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetReturnType2() {
        System.out.println("getReturnType");
        APIMethod instance = testInstaces[2];
        String expResult = "java.io.File";
        String result = instance.getReturnType();
        assertEquals(expResult, result);
    }    
    
    /**
     * Test of getThrown method, of class APIMethod.
     */
    @Test
    public void testGetThrown() {
        System.out.println("getThrown");        
        APIMethod instance = testInstaces[3];
        List expResult = new LinkedList();
        expResult.add("java.lang.Exception");
        expResult.add("java.io.IOException");
        List result = instance.getThrown();
        assertEquals(expResult, result);
    }
}
