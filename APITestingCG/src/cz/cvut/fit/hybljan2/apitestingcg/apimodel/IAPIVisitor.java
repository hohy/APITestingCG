package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

/**
 * Created by IntelliJ IDEA.
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
