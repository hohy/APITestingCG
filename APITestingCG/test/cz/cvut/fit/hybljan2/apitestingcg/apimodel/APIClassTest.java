package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.SortedSet;
import java.util.TreeSet;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class APIClassTest {
    
    private static APIClass[] testInstances = new APIClass[2];
    
    public APIClassTest() {
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
     * Test of addMethod method, of class APIClass.
     */
    @Test
    public void testAddMethod() {
        System.out.println("addMethod");
        
        List<String> params = new LinkedList<String>();  
        List<Modifier> modifiers = new LinkedList<Modifier>();
        modifiers.add(Modifier.PUBLIC);
        SortedSet<String> thrown = new TreeSet<String>();
        APIMethod method1 = new APIMethod("run", modifiers, params, "void", thrown); 
        
        SourceScanner sc = new SourceScanner("testres/testAPIClassRes/", "", "1.7");
        API api = sc.scan();
                
        APIClass instance = api.getPackages().get(0).getClasses().get(0);
        instance.addMethod(method1);
        
        String resultString = instance.toString();
        String expString = readFileToString("testres" + File.separator + "testAPIClassRes" + File.separator + "TestAPIClass2.string");
        
        assertEquals(expString, resultString);
    }

    /**
     * Test of addField method, of class APIClass.
     */
    @Test
    public void testAddField() {
        System.out.println("addField");
        
        List<Modifier> modifiers2 = new LinkedList<Modifier>();
        modifiers2.add(Modifier.PROTECTED);
        APIField field = new APIField("java.io.File", "source", modifiers2);
        
        SourceScanner sc = new SourceScanner("testres/testAPIClassRes/", "", "1.7");
        API api = sc.scan();
                
        APIClass instance = api.getPackages().get(0).getClasses().get(0);
        instance.addField(field);
        
        String resultString = instance.toString();
        String expString = readFileToString("testres" + File.separator + "testAPIClassRes" + File.separator + "TestAPIClass3.string");
        
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
        for(APIClass cls : testInstances) {
            String expResult = readFileToString("testres" + File.separatorChar + "testAPIClassRes" + File.separatorChar + cls.getName() + ".string");
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
        List<Modifier> modifiers = new LinkedList<Modifier>();
        modifiers.add(Modifier.PUBLIC);
        modifiers.add(Modifier.STATIC);
        modifiers.add(Modifier.FINAL);        
        expResult.add(new APIField("int", "SIZE", modifiers));
        List<Modifier> modifiers2 = new LinkedList<Modifier>();
        modifiers2.add(Modifier.PROTECTED);
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
        List expResult = new LinkedList();
        
        List<String> params = new LinkedList<String>();  
        List<Modifier> modifiers = new LinkedList<Modifier>();
        modifiers.add(Modifier.PUBLIC);
        SortedSet<String> thrown = new TreeSet<String>();
        APIMethod method1 = new APIMethod("run", modifiers, params, "void", thrown);        
        expResult.add(method1);
        
        List<String> params2 = new LinkedList<String>();
        params2.add("String");
        params2.add("int");
        List<Modifier> modifiers2 = new LinkedList<Modifier>();
        modifiers2.add(Modifier.PROTECTED);
        SortedSet<String> thrown2 = new TreeSet<String>();
        thrown2.add("java.io.IOException");       
        APIMethod method2 = new APIMethod("getList", modifiers2, params2, "java.util.List<Integer>", thrown2);
        expResult.add(method2);
        
        List result = instance.getMethods();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMethods2() {
        System.out.println("getMethods");
        APIClass instance = testInstances[0];
        List expResult = new LinkedList();        
        List result = instance.getMethods();
        assertEquals(expResult, result);
    }      
    
    /**
     * Test of getExtending method, of class APIClass.
     */
    @Test
    public void testGetExtending() {
        System.out.println("getExtending");
        APIClass instance = testInstances[1];
        String expResult = "javax.swing.JFrame";
        String result = instance.getExtending();
        assertEquals(expResult, result);        
    }

    @Test
    public void testGetExtending2() {
        System.out.println("getExtending");
        APIClass instance = testInstances[0];
        String expResult = null;
        String result = instance.getExtending();
        assertEquals(expResult, result);        
    }       
    
    /**
     * Test of getImplementing method, of class APIClass.
     */
    @Test
    public void testGetImplementing() {
        System.out.println("getImplementing");
        APIClass instance = testInstances[1];
        List expResult = new LinkedList();
        expResult.add("Runnable");
        
        List result = instance.getImplementing();
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

    private String readFileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null) {
                sb.append('\n').append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(APIClassTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(APIClassTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString().substring(1);
    }
}