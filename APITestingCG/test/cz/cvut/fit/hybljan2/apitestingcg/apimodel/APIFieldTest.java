/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.Set;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import java.util.HashSet;
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
public class APIFieldTest {
    
    private static APIField[] testInstances = new APIField[5];
    
    public APIFieldTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SourceScanner sc = new SourceScanner("testres/testAPIFieldRes/", "", "1.7");
        API api = sc.scan();
        api.getPackages().get(0).getClasses().get(0).getFields().toArray(testInstances);
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
     * Test of toString method, of class APIField.
     */
    @Test
    public void testToString() {
        System.out.println("toString");        
        String expResults[] = {
            "protected final double DOUBLE_CONST",
            "public static final int INT_CONST",
            "public static final Object OBJECT_CONST",            
            "public java.io.File file",
            "protected float floatVar",            
        };
        for (int i = 0; i < expResults.length; i++) {
            String expResult = expResults[i];
            String result = testInstances[i].toString();
            assertEquals(expResult, result);
        }
    }
    
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        boolean expResult = true;
        
        Set<Modifier> modifiersA = new HashSet<Modifier>();
        Set<Modifier> modifiersB = new HashSet<Modifier>();
        modifiersA.add(Modifier.PUBLIC);                
        modifiersA.add(Modifier.FINAL);
        modifiersA.add(Modifier.STATIC);
        modifiersB.add(Modifier.STATIC);
        modifiersB.add(Modifier.FINAL);
        modifiersB.add(Modifier.PUBLIC);
        
        APIField instanceA = new APIField("java.io.File", "src", modifiersA);
        APIField instanceB = new APIField("java.io.File", "src", modifiersB);
        
        boolean result = instanceA.equals(instanceB);
        
        assertEquals(result, expResult);
    }
    
    @Test
    public void testEquals2() {
        System.out.println("equals");
        boolean expResult = false;
        
        Set<Modifier> modifiersA = new HashSet<Modifier>();
        Set<Modifier> modifiersB = new HashSet<Modifier>();
        modifiersA.add(Modifier.PUBLIC);        
        modifiersB.add(Modifier.PROTECTED);        
        APIField instanceA = new APIField("java.io.File", "src", modifiersA);
        APIField instanceB = new APIField("java.io.File", "src", modifiersB);
        
        boolean result = instanceA.equals(instanceB);
        
        assertEquals(result, expResult);
    }    
}
