package cz.cvut.fit.hybljan2.apitestingcg;

import cz.cvut.fit.hybljan2.apitestingcg.configuration.ConfigurationReader;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ApiViewConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.ByteCodeScanner;
import cz.cvut.fit.hybljan2.apitestingcg.generator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.generator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.generator.InstantiatorGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import cz.cvut.fit.hybljan2.apitestingcg.view.APIViewForm;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jan Hýbl
 */
public class APITestingCG {    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        
        String pathToConfigFile = "configuration.xml";
        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-c")) {  // read path to configuration file
                pathToConfigFile = args[i+1];
            }
            // there will be other args processing
        }                   
        
        Map<String, API> apiMap = new HashMap<String, API>();
        
        ConfigurationReader configuration = new ConfigurationReader();
        configuration.parseConfiguration(pathToConfigFile);
        
        APIScanner scanner = null;
        APIScanner sourceScanner = new SourceScanner();
        APIScanner bytecodeScanner = new ByteCodeScanner();
        for(ScannerConfiguration sc : configuration.getApiConfigurations()) {
            switch(sc.getSource()) {
                case SOURCECODE: 
                    scanner = sourceScanner;
                    break;
                case BYTECODE:
                    scanner = bytecodeScanner;
                    break;
            }
            scanner.setConfiguration(sc);
            API api = scanner.scan();
            System.out.println("Loaded api: " + sc.getId());
            apiMap.put(sc.getId(), api);
        }
        
        for(ApiViewConfiguration ac : configuration.getApiViewConfigurations()) {
            System.out.println("Creating new ApiViewForm: " + ac.getApiId());
            new APIViewForm(apiMap.get(ac.getApiId())).setVisible(true);
        }   
        Generator g = new InstantiatorGenerator();
        g.generate(apiMap.get("testlib src"));        
        g = new ExtenderGenerator();
        g.generate(apiMap.get("testlib src"));
    }
}
