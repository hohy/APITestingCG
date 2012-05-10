package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import org.junit.*;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Jan Hybl
 */
public class APIClassTest {

    private static APIClass[] testInstances = new APIClass[5];

    public APIClassTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SourceScanner sc = new SourceScanner("testres/testAPIClassRes/", "", "1.7");
        API api = sc.scan();
        api.getPackages().first().getClasses().toArray(testInstances);
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
     * Test of addMethod method, of class APIClass.
     */
    @Test
    public void testAddMethod() {
        System.out.println("addMethod");

        List<String> params = new LinkedList<String>();
        List<APIModifier> modifiers = new LinkedList<>();
        modifiers.add(APIModifier.PUBLIC);
        List<String> thrown = new LinkedList<String>();
        APIMethod method1 = new APIMethod("run", modifiers, params, "void", thrown);

        SourceScanner sc = new SourceScanner("testres/testAPIClassRes/", "", "1.7");
        API api = sc.scan();

        APIClass instance = api.getPackages().first().getClasses().first();
        instance.addMethod(method1);

        String resultString = instance.toString();
        String expString = TestUtils.readFileToString("testres" + File.separator + "testAPIClassRes" + File.separator + "TestAPIClass2.string");

        assertEquals(expString, resultString);
    }

    /**
     * Test of addField method, of class APIClass.
     */
    @Test
    public void testAddField() {
        System.out.println("addField");

        List<APIModifier> modifiers2 = new LinkedList<>();
        modifiers2.add(APIModifier.PROTECTED);
        APIField field = new APIField("java.io.File", "source", modifiers2);

        SourceScanner sc = new SourceScanner("testres/testAPIClassRes/", "", "1.7");
        API api = sc.scan();

        APIClass instance = api.getPackages().first().getClasses().first();
        instance.addField(field);

        String resultString = instance.toString();
        String expString = TestUtils.readFileToString("testres" + File.separator + "testAPIClassRes" + File.separator + "TestAPIClass3.string");

        assertEquals(expString, resultString);
    }

    /**
     * Test of toString method, of class APIClass.
     * Every class has expected toString result saved in file ClassName.string
     * in testres directory. These files are loaded and content is compared to
     * real result of toString method of class.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        for (APIClass cls : testInstances) {
            String expResult = TestUtils.readFileToString("testres" + File.separatorChar + "testAPIClassRes" + File.separatorChar + cls.getName() + ".string");
            String result = cls.toString();
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of getFields method, of class APIClass.
     */
    @Test
    public void testGetFields() {
        System.out.println("getFields");
        APIClass instance = testInstances[1];

        Set expResult = new TreeSet();
        List<APIModifier> modifiers = new LinkedList<>();
        modifiers.add(APIModifier.PUBLIC);
        modifiers.add(APIModifier.STATIC);
        modifiers.add(APIModifier.FINAL);
        expResult.add(new APIField("int", "SIZE", modifiers));
        List<APIModifier> modifiers2 = new LinkedList<>();
        modifiers2.add(APIModifier.PROTECTED);
        expResult.add(new APIField("java.io.File", "source", modifiers2));

        Set result = instance.getFields();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMethods method, of class APIClass.
     */
    @Test
    public void testGetMethods() {
        System.out.println("getMethods");
        APIClass instance = testInstances[1];
        SortedSet expResult = new TreeSet();

        List<String> params = new LinkedList<String>();
        List<APIModifier> modifiers = new LinkedList<>();
        modifiers.add(APIModifier.PUBLIC);
        List<String> thrown = new LinkedList<String>();
        APIMethod method1 = new APIMethod("run", modifiers, params, "void", thrown);
        expResult.add(method1);

        List<String> params2 = new LinkedList<String>();
        params2.add("java.lang.String");
        params2.add("int");
        List<APIModifier> modifiers2 = new LinkedList<>();
        modifiers2.add(APIModifier.PROTECTED);
        List<String> thrown2 = new LinkedList<String>();
        thrown2.add("java.io.IOException");
        APIMethod method2 = new APIMethod("getList", modifiers2, params2, "java.util.List<java.lang.Integer>", thrown2);
        expResult.add(method2);

        Set result = instance.getMethods();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMethods2() {
        System.out.println("getMethods");
        APIClass instance = testInstances[0];
        Set expResult = new TreeSet();
        Set result = instance.getMethods();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExtending method, of class APIClass.
     */
    @Test
    public void testGetExtending() {
        System.out.println("getExtending");
        APIClass instance = testInstances[1];
        APIType expResult = new APIType("javax.swing.JFrame");
        APIType result = instance.getExtending();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetExtending2() {
        System.out.println("getExtending");
        APIClass instance = testInstances[0];
        String expResult = null;
        APIType result = instance.getExtending();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetConstructors() {
        APIClass instance = testInstances[0];
        SortedSet<APIMethod> expResult = new TreeSet<APIMethod>();
        List<String> thrown = new LinkedList<String>();
        List<APIModifier> pubmod = new LinkedList<>();
        pubmod.add(APIModifier.PUBLIC);
        APIMethod defcon = new APIMethod("TestAPIClass", pubmod, new LinkedList<String>(), "testAPIClassRes.TestAPIClass", thrown);
        expResult.add(defcon);
        SortedSet<APIMethod> result = instance.getConstructors();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetConstructors2() {
        APIClass instance = testInstances[1];
        SortedSet<APIMethod> expResult = new TreeSet<APIMethod>();
        List<String> thrown = new LinkedList<String>();
        List<APIModifier> pubmod = new LinkedList<>();
        pubmod.add(APIModifier.PUBLIC);
        List<String> params = new LinkedList<>();
        params.add("int");
        expResult.add(new APIMethod("TestAPIClassB", pubmod, params, "testAPIClassRes.TestAPIClassB", thrown));
        params = new LinkedList<>();
        params.add("java.io.File");
        params.add("int");
        expResult.add(new APIMethod("TestAPIClassB", pubmod, params, "testAPIClassRes.TestAPIClassB", thrown));
        expResult.add(new APIMethod("TestAPIClassB", pubmod, new LinkedList<String>(), "testAPIClassRes.TestAPIClassB", thrown));
        SortedSet<APIMethod> result = instance.getConstructors();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImplementing method, of class APIClass.
     */
    @Test
    public void testGetImplementing() {
        System.out.println("getImplementing");
        APIClass instance = testInstances[1];
        List<APIType> expResult = new LinkedList();
        expResult.add(new APIType("java.lang.Runnable"));

        List<APIType> result = instance.getImplementing();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetImplementing2() {
        System.out.println("getImplementing");
        APIClass instance = testInstances[0];
        List expResult = new LinkedList();
        List result = instance.getImplementing();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullName method, of class APIClass.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        APIClass instance = testInstances[1];
        String expResult = "testAPIClassRes.TestAPIClassB";
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFullName2() {
        System.out.println("getFullName");
        APIClass instance = testInstances[0];
        String expResult = "testAPIClassRes.TestAPIClass";
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        APIClass instance = testInstances[0];
        APIClass obj = new APIClass("testAPIClassRes.TestAPIClass");
        obj.addDefaultConstructor();
        boolean result = instance.equals(obj);
        boolean expResult = true;
        assertEquals(result, expResult);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals");
        APIClass instance = testInstances[1];
        APIClass obj = new APIClass("testAPIClassRes.TestAPIClassB");
        obj.addDefaultConstructor();

        List<String> thrown = new LinkedList<String>();
        List<APIModifier> pubmod = new LinkedList<>();

        pubmod.add(APIModifier.PUBLIC);
        List<String> params = new LinkedList<>();
        params.add("int");
        obj.addConstructor(new APIMethod("TestAPIClassB", pubmod, params, "testAPIClassRes.TestAPIClassB", thrown));
        params = new LinkedList<>();
        params.add("java.io.File");
        params.add("int");
        obj.addConstructor(new APIMethod("TestAPIClassB", pubmod, params, "testAPIClassRes.TestAPIClassB", thrown));
        obj.addConstructor(new APIMethod("TestAPIClassB", pubmod, new LinkedList<String>(), "testAPIClassRes.TestAPIClassB", thrown));

        obj.setModifiers(pubmod);
        obj.setExtends("javax.swing.JFrame");

        List<String> implement = new LinkedList<String>();
        implement.add("java.lang.Runnable");
        obj.setImplementing(implement);

        List<APIModifier> psfmod = new LinkedList<>();
        psfmod.add(APIModifier.PUBLIC);
        psfmod.add(APIModifier.STATIC);
        psfmod.add(APIModifier.FINAL);

        APIField size = new APIField("int", "SIZE", psfmod);
        obj.addField(size);
        List<APIModifier> promod = new LinkedList<>();
        promod.add(APIModifier.PROTECTED);
        APIField source = new APIField("java.io.File", "source", promod);
        obj.addField(source);
        thrown = new LinkedList<String>();
        APIMethod run = new APIMethod("run", pubmod, new LinkedList<String>(), "void", thrown);
        obj.addMethod(run);

        params = new LinkedList<String>();
        params.add("java.lang.String");
        params.add("int");
        List<String> thrown2 = new LinkedList<String>();
        thrown2.add("java.io.IOException");
        APIMethod getList = new APIMethod("getList", promod, params, "java.util.List<java.lang.Integer>", thrown2);
        obj.addMethod(getList);
        boolean result = instance.equals(obj);
        boolean expResult = true;
        assertEquals(result, expResult);
    }

    @Test
    public void testGetPackageName() {
        APIClass instance = new APIClass("java.io.File");
        String result = instance.getPackageName();
        String expResult = "java.io";

        assertEquals(expResult, result);

    }

    @Test
    public void testGetFullNameWithTypeParams() throws Exception {
        APIClass instance = new APIClass("java.util.List");
        String[] strings = {"java.lang.String"};
        instance.getTypeParamsMap().put("T", strings);
        String expected = "java.util.List<T>";
        String result = instance.getFullNameWithTypeParams();
        assertEquals(expected, result);
    }

    @Test
    public void testGetFullNameWithTypeParams2() throws Exception {
        APIClass instance = new APIClass("java.util.Map");
        String[] strings = {"java.lang.String"};
        instance.getTypeParamsMap().put("T", strings);
        instance.getTypeParamsMap().put("U", strings);
        String expected = "java.util.Map<T, U>";
        String result = instance.getFullNameWithTypeParams();
        assertEquals(expected, result);
    }
}
