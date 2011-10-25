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
        

        API api = scanner.scan();
        new APIViewForm(api).setVisible(true);
    }
}
