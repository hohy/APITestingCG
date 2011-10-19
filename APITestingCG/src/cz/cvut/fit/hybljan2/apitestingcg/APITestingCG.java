package cz.cvut.fit.hybljan2.apitestingcg;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.view.APIViewForm;

/**
 *
 * @author Jan Hýbl
 */
public class APITestingCG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Scanner sc = new SourceScanner("/media/data/javalibs/SmallSQL/src/smallsql/database", "", "1.5");
        //APIScanner sc = new SourceScanner("/home/hohy/NetBeansProjects/APITestingCG/testlibs/Lib1/TestLib1", "", "1.6");
        //APIScanner sc = new ByteCodeScanner("/home/hohy/NetBeansProjects/APITestingCG/testlibs/Lib1/TestLib1/dist/TestLib1.jar");
        APIScanner sc = new ByteCodeScanner("/media/data/javalibs/SmallSQL/smallsql.jar");
        API api = sc.scan();
        //new APIViewForm(api).setVisible(true);
    }
}
