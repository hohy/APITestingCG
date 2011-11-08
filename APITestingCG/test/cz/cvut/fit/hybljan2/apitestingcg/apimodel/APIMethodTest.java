package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.SortedSet;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.util.TreeSet;
import java.util.Set;
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
        api.getPackages().first().getClasses().first().getMethods().toArray(testInstaces);
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
        Set expResult = new TreeSet();
        expResult.add("java.lang.Exception");
        expResult.add("java.io.IOException");
        Set result = instance.getThrown();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        APIMethod instance = testInstaces[0];
        List<Modifier> pubmod = new LinkedList<Modifier>();
        pubmod.add(Modifier.PUBLIC);
        SortedSet<String> thrown = new TreeSet<String>();
        APIMethod obj = new APIMethod("methodA", pubmod, new LinkedList<String>(), "void", thrown);
        boolean result = instance.equals(obj);
        boolean expResults = true;
        assertEquals(expResults, result);
        instance = testInstaces[1];
        pubmod.add(Modifier.STATIC);
        pubmod.add(Modifier.FINAL);
        List<String> params = new LinkedList<String>();
        params.add("int");
        params.add("int");
        obj = new APIMethod("methodB", pubmod, params, "int", thrown);
        result = instance.equals(obj);
        assertEquals(expResults, result);
        instance = testInstaces[2];
        pubmod.clear();
        pubmod.add(Modifier.PROTECTED);
        params.clear();
        params.add("java.util.List");
        params.add("float");
        params.add("java.util.Queue");
        obj = new APIMethod("methodC", pubmod, params, "java.io.File", thrown);
        result = instance.equals(obj);
        assertEquals(expResults, result);
        instance = testInstaces[3];
        pubmod.clear();
        pubmod.add(Modifier.PUBLIC);
        params.clear();
        thrown.add("java.io.IOException");
        thrown.add("java.lang.Exception");
        obj = new APIMethod("methodD", pubmod, params, "void", thrown);
        result = instance.equals(obj);
        assertEquals(expResults, result);        
    }
}
