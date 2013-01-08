/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;

/**
 * @author Jan Hýbl
 */
public interface APIScanner {
    public API scan();

    public void setConfiguration(ScannerConfiguration sc);
}
