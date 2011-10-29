/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
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
    
    private static APIField[] testInstances = new APIField[4];
    
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
            "protected float floatVar",                
        };
        for (int i = 0; i < expResults.length; i++) {
            String expResult = expResults[i];
            String result = testInstances[i].toString();
            assertEquals(expResult, result);
        }
    }
}
