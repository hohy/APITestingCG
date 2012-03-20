/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import org.junit.*;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * @author hohy
 */
public class APITest {

    private static API testInstance;

    public APITest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SourceScanner sc = new SourceScanner("testres/testAPIPackageRes/", "", "1.7");
        testInstance = sc.scan();
        testInstance.name = "testapi";
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
     * Test of addPackage method, of class API.
     */
    @Test
    public void testAddPackage() {
        System.out.println("addPackage");
        APIPackage pkg = new APIPackage("test.package");
        API instance = new API("test api");
        instance.addPackage(pkg);
        assertEquals(pkg, instance.getPackages().first());
    }

    /**
     * Test of toString method, of class API.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        API instance = testInstance;
        String expResult = "testapi null:\n\npackage testres.testAPIPackageRes\n\n" +
                "public class testres.testAPIPackageRes.TestClassA\n" +
                " public constructor testres.testAPIPackageRes.TestClassA TestClassA()\n" +
                "public class testres.testAPIPackageRes.TestClassB\n" +
                " public constructor testres.testAPIPackageRes.TestClassB TestClassB()";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPackages method, of class API.
     */
    @Test
    public void testGetPackages() {
        System.out.println("getPackages");
        API instance = testInstance;
        Set expResult = new TreeSet();
        APIPackage p = new APIPackage("testres.testAPIPackageRes");
        p.addClass(new APIClass("testres.testAPIPackageRes.TestClassA"));
        p.addClass(new APIClass("testres.testAPIPackageRes.TestClassB"));
        expResult.add(p);
        Set result = instance.getPackages();
        assertEquals(expResult, result);
    }
}
