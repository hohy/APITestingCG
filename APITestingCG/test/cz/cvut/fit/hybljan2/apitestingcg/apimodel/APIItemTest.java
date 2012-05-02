package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem.Kind;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import org.junit.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
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
        List expResult = new LinkedList<APIModifier>();
        expResult.add(APIModifier.PUBLIC);
        expResult.add(APIModifier.FINAL);
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

    public class APIItemImpl extends APIItem {

        public APIItemImpl() {
            name = "somePkgName.SomeAPIItem";
            modifiers = new LinkedList<APIModifier>();
            modifiers.add(APIModifier.PUBLIC);
            modifiers.add(APIModifier.FINAL);
            kind = Kind.CLASS;
        }

        @Override
        public void accept(IAPIVisitor visitor) {

        }
    }
}
