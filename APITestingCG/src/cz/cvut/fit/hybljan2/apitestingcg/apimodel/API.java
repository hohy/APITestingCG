package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.LinkedList;
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
        packages = new LinkedList<APIPackage>();
    }
    
    public void addPackage(APIPackage pkg) {
        packages.add(pkg);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("API ").append(name).append(":\n");
        for(APIPackage p : packages) sb.append("  ").append(p).append('\n');
        return sb.toString();
    }       
}
