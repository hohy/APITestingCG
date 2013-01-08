/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Developmen and Distribution License (CDDL). You can obtain a copy of the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import org.junit.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
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
        String[] expResults = {
                "public method void methodA()",
                "public static final method int methodB(int,int)",
                "protected method java.io.File methodC(java.util.List,float,java.util.Queue)",
                "public method void methodD() throws java.lang.Exception",
                "abstract public method <G java.lang.Object, H java.util.List & java.util.Set> java.util.Set<G> write4(java.util.List<H>)"
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
        APIMethod instance = testInstaces[2];
        List<APIMethodParameter> expResult = new LinkedList<>();
        expResult.add(new APIMethodParameter("itemList", "java.util.List"));
        expResult.add(new APIMethodParameter("x", "float"));
        expResult.add(new APIMethodParameter("queue", "java.util.Queue"));
        List result = instance.getParameters();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReturnType method, of class APIMethod.
     */
    @Test
    public void testGetReturnType() {
        APIMethod instance = testInstaces[1];
        APIType expResult = new APIType("int");
        APIType result = instance.getReturnType();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetReturnType2() {
        APIMethod instance = testInstaces[2];
        APIType expResult = new APIType("java.io.File");
        APIType result = instance.getReturnType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThrown method, of class APIMethod.
     */
    @Test
    public void testGetThrown() {
        APIMethod instance = testInstaces[3];
        List expResult = new LinkedList();
        expResult.add("java.lang.Exception");
        //expResult.add("java.io.IOException");
        List result = instance.getThrown();
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals() {
        APIMethod instance = testInstaces[0];
        List<APIModifier> pubmod = new LinkedList<>();
        pubmod.add(APIModifier.PUBLIC);
        List<String> thrown = new LinkedList<String>();
        APIMethod obj = new APIMethod("methodA", pubmod, new LinkedList<String>(), "void", thrown);
        boolean result = instance.equals(obj);
        boolean expResults = true;
        assertEquals(expResults, result);
        instance = testInstaces[1];
        pubmod.add(APIModifier.STATIC);
        pubmod.add(APIModifier.FINAL);
        List<String> params = new LinkedList<String>();
        params.add("int");
        params.add("int");
        obj = new APIMethod("methodB", pubmod, params, "int", thrown);
        result = instance.equals(obj);
        assertEquals(expResults, result);
        instance = testInstaces[2];
        pubmod.clear();
        pubmod.add(APIModifier.PROTECTED);
        params.clear();
        params.add("java.util.List");
        params.add("float");
        params.add("java.util.Queue");
        obj = new APIMethod("methodC", pubmod, params, "java.io.File", thrown);
        result = instance.equals(obj);
        assertEquals(expResults, result);

        params.clear();
        params.add("java.util.List");
        params.add("int");
        params.add("java.util.Queue");
        obj = new APIMethod("methodC", pubmod, params, "java.io.File", thrown);
        result = instance.equals(obj);
        expResults = false;
        assertEquals(expResults, result);

        expResults = true;
        instance = testInstaces[3];
        pubmod.clear();
        pubmod.add(APIModifier.PUBLIC);
        params.clear();
        thrown.add("java.lang.Exception");
        obj = new APIMethod("methodD", pubmod, params, "void", thrown);
        result = instance.equals(obj);
        assertEquals(expResults, result);
    }
}
