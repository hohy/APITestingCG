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
        // TODO code application logic here
        Scanner sc = new Scanner("/media/data/javalibs/SmallSQL/src/smallsql/database", "", "1.5");
        API api = sc.scan();
        new APIViewForm(api).setVisible(true);
    }
}
