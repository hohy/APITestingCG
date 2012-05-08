package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 3.5.12
 * Time: 10:53
 */
public class APIModifierTest {

    @Test
    public void testCheckAccessModifier() {
        APIItem testItem = new APIClass("lib.TestClass");
        testItem.getModifiers().add(APIModifier.PUBLIC);
        assertTrue(APIModifier.checkAccessLevel(APIModifier.PUBLIC, testItem));
        assertTrue(APIModifier.checkAccessLevel(APIModifier.PROTECTED, testItem));
        assertTrue(APIModifier.checkAccessLevel(APIModifier.PRIVATE,testItem));

        testItem.getModifiers().clear();
        testItem.getModifiers().add(APIModifier.PROTECTED);
        assertFalse(APIModifier.checkAccessLevel(APIModifier.PUBLIC, testItem));
        assertTrue(APIModifier.checkAccessLevel(APIModifier.PROTECTED, testItem));
        assertTrue(APIModifier.checkAccessLevel(APIModifier.PRIVATE,testItem));

        testItem.getModifiers().clear();
        testItem.getModifiers().add(APIModifier.PRIVATE);
        assertFalse(APIModifier.checkAccessLevel(APIModifier.PUBLIC, testItem));
        assertFalse(APIModifier.checkAccessLevel(APIModifier.PROTECTED, testItem));
        assertTrue(APIModifier.checkAccessLevel(APIModifier.PRIVATE,testItem));
    }

}
