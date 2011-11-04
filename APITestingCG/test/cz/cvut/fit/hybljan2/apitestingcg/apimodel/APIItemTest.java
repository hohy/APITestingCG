package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.List;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem.Kind;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.util.HashSet;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
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
public class APIItemTest {
    
    private static APIItem[] testInstances = new APIItem[2];
    
    public APIItemTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SourceScanner sc = new SourceScanner("testres/testAPIClassRes/", "", "1.7");
        API api = sc.scan();
        api.getPackages().get(0).getClasses().toArray(testInstances);        
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
     * Test of getName method, of class APIItem.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        APIItem instance = new APIItemImpl();
        String expResult = "somePkgName.SomeAPIItem";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getModifiers method, of class APIItem.
     */
    @Test
    public void testGetModifiers() {
        System.out.println("getModifiers");
        APIItem instance = new APIItemImpl();
        List expResult = new LinkedList<Modifier>();
        expResult.add(Modifier.PUBLIC);
        expResult.add(Modifier.FINAL);
        List result = instance.getModifiers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class APIItem.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        APIItem instance = new APIItemImpl();
        Kind expResult = Kind.CLASS;
        Kind result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of findFullClassName method, of class APIItem.
     */
    @Test
    public void testGetFullClassName() {
        System.out.println("getFullClassName");
        String simpleName = "List";
        Map<String, String> importsMap = new HashMap<String, String>();
        importsMap.put("List", "java.util.List");
        APIItem instance = new APIItemImpl();
        String expResult = "java.util.List";
        String result = instance.findFullClassName(simpleName, importsMap);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFullClassName2() {
        System.out.println("getFullClassName");
        String simpleName = "List<File>";
        Map<String, String> importsMap = new HashMap<String, String>();
        importsMap.put("List", "java.util.List");
        importsMap.put("File", "java.io.File");
        APIItem instance = new APIItemImpl();
        String expResult = "java.util.List<java.io.File>";
        String result = instance.findFullClassName(simpleName, importsMap);
        assertEquals(expResult, result);    
    }
    
    public class APIItemImpl extends APIItem {

        public APIItemImpl() {
            name = "somePkgName.SomeAPIItem";
            modifiers = new LinkedList<Modifier> ();
            modifiers.add(Modifier.PUBLIC);
            modifiers.add(Modifier.FINAL);            
            kind = Kind.CLASS;            
        }
        
    }
}
