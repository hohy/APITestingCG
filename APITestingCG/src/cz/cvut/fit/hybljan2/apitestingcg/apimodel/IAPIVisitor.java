/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

/**
 * Interface for visitors of APIModel classes.
 * <p/>
 * User: Jan Hýbl
 * Date: 3.2.12
 * Time: 16:13
 */
public interface IAPIVisitor {
    void visit(API api);

    void visit(APIClass apiClass);

    void visit(APIField apiField);

    void visit(APIPackage apiPackage);

    void visit(APIMethod method);
}
