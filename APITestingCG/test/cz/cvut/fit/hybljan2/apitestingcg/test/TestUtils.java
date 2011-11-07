package cz.cvut.fit.hybljan2.apitestingcg.test;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClassTest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class contains some useful methods for testing.
 * @author Jan Hýbl
 */
public class TestUtils {
    public static String readFileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null) {
                sb.append('\n').append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(APIClassTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(APIClassTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString().substring(1);
    }    
}
