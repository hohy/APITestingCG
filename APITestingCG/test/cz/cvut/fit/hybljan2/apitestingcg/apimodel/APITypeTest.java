/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 27.4.12
 * Time: 14:12
 */
public class APITypeTest {

    @Test
    public void testAddTypeParameter() {
        APIType instance = new APIType("java.util.List");
        assertEquals("java.util.List", instance.toString());

        instance.addTypeParameter(new APIType("java.lang.String"));
        assertEquals("java.util.List<java.lang.String>", instance.toString());

        instance = new APIType("java.util.Map");
        instance.addTypeParameter(new APIType("java.lang.Integer"));
        instance.addTypeParameter(new APIType("java.lang.String"));
        assertEquals("java.util.Map<java.lang.Integer, java.lang.String>", instance.toString());
    }

    @Test
    public void testIsArray() {
        APIType noArray = new APIType("NoArray");
        assertFalse(noArray.isArray());

        noArray = new APIType("NoArray", false);
        assertFalse(noArray.isArray());

        APIType withArray = new APIType("WithArray", true);
        assertTrue(withArray.isArray());
    }

    @Test
    public void testToString() {
        APIType instance = new APIType("java.util.Map");
        instance.addTypeParameter(new APIType("java.lang.String"));
        APIType typeArg = new APIType("java.util.List");
        typeArg.addTypeParameter(new APIType("java.lang.Integer", true));
        instance.addTypeParameter(typeArg);
        
        String expected = "java.util.Map<java.lang.String, java.util.List<java.lang.Integer[]>>";
        assertEquals(expected, instance.toString());
    }
}
