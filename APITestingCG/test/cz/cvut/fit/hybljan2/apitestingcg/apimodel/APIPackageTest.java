/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import java.io.File;
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
 * @author hohy
 */
public class APIPackageTest {
    
    private static APIPackage[] testInstances = new APIPackage[1];
    
    public APIPackageTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SourceScanner sc = new SourceScanner("testres/testAPIPackageRes/", "", "1.7");
        API api = sc.scan();
        api.getPackages().toArray(testInstances);        
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
     * Test of addClass method, of class APIPackage.
     */
    @Test
    public void testAddClass() {
        System.out.println("addClass");
        APIClass clazz = new APIClass("Cls");
        APIPackage instance = new APIPackage("test.package");
        instance.addClass(clazz);
        assertEquals(instance.getClasses().get(0), clazz);
    }

    /**
     * Test of toString method, of class APIPackage.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        APIPackage instance = testInstances[0];
        String expResult = TestUtils.readFileToString("testres" + File.separator + "testAPIPackageRes" + File.separator + "testAPIPackageRes.string");
        String result = instance.toString();  
        assertEquals(expResult, result);
    }

    /**
     * Test of getClasses method, of class APIPackage.
     */
    @Test
    public void testGetClasses() {
        System.out.println("getClasses");
        APIPackage instance = testInstances[0];
        List expResult = new LinkedList();
        APIClass clsA = new APIClass("testres.testAPIPackageRes.TestClassA");
        APIClass clsB = new APIClass("testres.testAPIPackageRes.TestClassB");        
        expResult.add(clsA);
        expResult.add(clsB);
        List result = instance.getClasses();
        assertEquals(expResult, result);   
    }

    /**
     * Test of equals method, of class APIPackage.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        APIPackage obj = new APIPackage("testres.testAPIPackageRes");
        APIClass clsA = new APIClass("testres.testAPIPackageRes.TestClassA");
        APIClass clsB = new APIClass("testres.testAPIPackageRes.TestClassB"); 
        obj.addClass(clsA);
        obj.addClass(clsB);
        APIPackage instance = testInstances[0];
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);        
    }
    
}
