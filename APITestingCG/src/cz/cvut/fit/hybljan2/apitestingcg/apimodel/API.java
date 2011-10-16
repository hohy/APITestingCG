package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.List;

/**
 * Represents API of library or framework. Contains classes and methods library 
 * provides to client.
 * @author Jan Hýbl
 */
public class API {
    
    private String name;
    private List<APIPackage> packages;

    public API(String name) {
        this.name = name;       
    }
    
    public void addPackage(APIPackage pkg) {
        packages.add(pkg);
    }
}
