package cz.cvut.fit.hybljan2.apitestingcg;

import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.ByteCodeScanner;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
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
        
        APIScanner scanner = null;
        
        if(args.length > 0) {
            if(args[0].equals("source")) {
                scanner = new SourceScanner(args[1], "", "1.7");
            } else if (args[0].equals("bytecode")) {
                scanner = new ByteCodeScanner(args[1]);
            }
        } else {
            System.out.println("Please, run program with argumetns [source|bytecode] [pathToSourceOrJar]");
            return;
        }
        
        //APIScanner sc = new SourceScanner("/media/data/javalibs/SmallSQL/src/smallsql/database", "", "1.7");
        //APIScanner sc = new SourceScanner("/home/hohy/NetBeansProjects/APITestingCG/testlibs/Lib1/TestLib1", "", "1.6");
        //APIScanner scbc = new ByteCodeScanner("/home/hohy/NetBeansProjects/APITestingCG/testlibs/Lib1/TestLib1/dist/TestLib1.jar");
        //APIScanner sc = new ByteCodeScanner("/media/data/javalibs/SmallSQL/smallsql.jar");
//        APIScanner sc = new SourceScanner("/media/data/javalibs/apache-log4j-1.2.16/src/main/java",
//                "/media/data/javalibs/javamail-1.4.1/mail.jar;/media/data/javalibs/geronimo-jms_1.1_spec-1.1.jar",
//                "1.7");
        //APIScanner sc = new SourceScanner("sa", "", "1.7");
        API api = scanner.scan();
        new APIViewForm(api).setVisible(true);
    }
}
