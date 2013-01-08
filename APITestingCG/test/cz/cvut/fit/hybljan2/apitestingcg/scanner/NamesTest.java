/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 25.9.12
 * Time: 18:34
 */
public class NamesTest {

    static API apiSrc;
    static API apiBc;

    @BeforeClass
    public static void Setup() throws IOException {
        ScannerConfiguration srcScannerConfiguration = new ScannerConfiguration();
        srcScannerConfiguration.setApiName("Names test");
        srcScannerConfiguration.setPath("testres/names/src");
        srcScannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        srcScannerConfiguration.setSourceVersion("1.7");
        srcScannerConfiguration.setId("names");

        APIScanner srcScanner = new SourceScanner();
        srcScanner.setConfiguration(srcScannerConfiguration);

        apiSrc = srcScanner.scan();

        ScannerConfiguration bcScannerConfiguration = new ScannerConfiguration();
        bcScannerConfiguration.setApiName("Names test");
        bcScannerConfiguration.setPath("testres/names/dist/names.jar");
        bcScannerConfiguration.setSource(ScannerConfiguration.APISource.SOURCECODE);
        bcScannerConfiguration.setSourceVersion("1.7");
        bcScannerConfiguration.setId("names");

        APIScanner bcScanner = new ByteCodeScanner();
        bcScanner.setConfiguration(bcScannerConfiguration);

        apiBc = bcScanner.scan();
    }

    @Test
    public void NamesSrc() {

        APIClass cls = apiSrc.getPackages().first().getClasses().first();
        assertEquals("lib.$Main",cls.getFullName());

        APIField f = cls.getFields().iterator().next();
        assertEquals("lib.$Main.$NestedClass", f.getVarType().getName());

        APIMethod m = cls.getMethods().iterator().next();
        assertEquals("lib.$Main.$NestedClass", m.getReturnType().getName());

        cls = cls.getNestedClasses().first();
        assertEquals("lib.$Main.$NestedClass",cls.getFullName());
        cls = cls.getNestedClasses().first();
        assertEquals("lib.$Main.$NestedClass.$NestedClassL2", cls.getFullName());

    }


    @Test
    public void NamesBc() {

        APIClass cls = apiBc.getPackages().first().getClasses().first();
        assertEquals("lib.$Main",cls.getFullName());

        APIField f = cls.getFields().iterator().next();
        assertEquals("lib.$Main.$NestedClass", f.getVarType().getName());

        APIMethod m = cls.getMethods().iterator().next();
        assertEquals("lib.$Main.$NestedClass", m.getReturnType().getName());

        cls = cls.getNestedClasses().first();
        assertEquals("lib.$Main.$NestedClass",cls.getFullName());
        cls = cls.getNestedClasses().first();
        assertEquals("lib.$Main.$NestedClass.$NestedClassL2",cls.getFullName());

    }
            

}
