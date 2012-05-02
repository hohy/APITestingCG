/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import org.junit.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author hohy
 */
public class APIFieldTest {

    private static APIField[] testInstances = new APIField[6];

    public APIFieldTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SourceScanner sc = new SourceScanner("testres/testAPIFieldRes/", "", "1.7");
        API api = sc.scan();
        api.getPackages().first().getClasses().first().getFields().toArray(testInstances);
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
                "public static final java.lang.Object OBJECT_CONST",
                "public java.io.File file",
                "protected float floatVar",
                "public java.util.Map<java.lang.String,java.util.List<java.io.File>> xfileListMap"
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

        List<APIModifier> modifiersA = new LinkedList<>();
        List<APIModifier> modifiersB = new LinkedList<>();
        modifiersA.add(APIModifier.PUBLIC);
        modifiersA.add(APIModifier.FINAL);
        modifiersA.add(APIModifier.STATIC);
        modifiersB.add(APIModifier.STATIC);
        modifiersB.add(APIModifier.FINAL);
        modifiersB.add(APIModifier.PUBLIC);

        APIField instanceA = new APIField("java.io.File", "src", modifiersA);
        APIField instanceB = new APIField("java.io.File", "src", modifiersB);

        boolean result = instanceA.equals(instanceB);

        assertEquals(result, expResult);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals");
        boolean expResult = false;

        List<APIModifier> modifiersA = new LinkedList<>();
        List<APIModifier> modifiersB = new LinkedList<>();
        modifiersA.add(APIModifier.PUBLIC);
        modifiersB.add(APIModifier.PROTECTED);
        APIField instanceA = new APIField("java.io.File", "src", modifiersA);
        APIField instanceB = new APIField("java.io.File", "src", modifiersB);

        boolean result = instanceA.equals(instanceB);

        assertEquals(result, expResult);
    }
}
