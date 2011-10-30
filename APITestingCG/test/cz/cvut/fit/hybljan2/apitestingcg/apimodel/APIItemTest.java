/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import java.util.HashMap;
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
 * @author hohy
 */
public class APIItemTest {
    
    public APIItemTest() {
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
     * Test of getName method, of class APIItem.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        APIItem instance = new APIItemImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifiers method, of class APIItem.
     */
    @Test
    public void testGetModifiers() {
        System.out.println("getModifiers");
        APIItem instance = new APIItemImpl();
        Set expResult = null;
        Set result = instance.getModifiers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class APIItem.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        APIItem instance = new APIItemImpl();
        Kind expResult = null;
        Kind result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassName method, of class APIItem.
     */
    @Test
    public void testGetClassName() {
        System.out.println("getClassName");
        Class c = null;
        APIItem instance = new APIItemImpl();
        String expResult = "";
        String result = instance.getClassName(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFullClassName method, of class APIItem.
     */
    @Test
    public void testGetFullClassName() {
        System.out.println("getFullClassName");
        String simpleName = "List";
        Map<String, String> importsMap = new HashMap<String, String>();
        importsMap.put("List", "java.util.List");
        APIItem instance = new APIItemImpl();
        String expResult = "java.util.List";
        String result = instance.getFullClassName(simpleName, importsMap);
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
        String result = instance.getFullClassName(simpleName, importsMap);
        assertEquals(expResult, result);    
    }
    
    public class APIItemImpl extends APIItem {
    }
}
