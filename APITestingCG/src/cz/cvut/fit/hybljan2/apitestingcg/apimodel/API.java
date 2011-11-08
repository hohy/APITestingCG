package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents API of library or framework. Contains classes and methods library 
 * provides to client.
 * @author Jan Hýbl
 */
public class API extends APIItem{
        
    private SortedSet<APIPackage> packages;
    private String version;

    public API(String name) {
        super.name = name; 
        packages = new TreeSet<APIPackage>();
        version = "";
    }    
    
    public API(String name, String version) {
        super.name = name; 
        packages = new TreeSet<APIPackage>();
        this.version = version;
    }
    
    public void addPackage(APIPackage pkg) {        
        packages.add(pkg);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(version).append(":\n");
        for(APIPackage p : packages) sb.append(p).append('\n');
        return sb.toString().substring(0,sb.length()-1);
    }

    public SortedSet<APIPackage> getPackages() {
        return packages;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final API other = (API) obj;
        if (this.packages != other.packages && (this.packages == null || !this.packages.equals(other.packages))) {
            return false;
        }
        return true;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }    
}
