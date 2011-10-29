package cz.cvut.fit.hybljan2.apitestingcg.configuration;

/**
 * Class stores information how to scan API and other information about API for
 * scanner
 * @author Jan Hybl
 */
public class ScannerConfiguration {

    public enum APISource {SOURCECODE, BYTECODE}
    
    private APISource source;
    private String apiName = "";
    private String apiVersion = "";
    private String path = "";
    private String classpath = "";
    private String sourceVersion = "";
    private String id = "";

    public String getId() {
        if(id != null) return id;
        else return apiName + " " + apiVersion + " " + source;
    }    
    
    public void setId(String id) {
        this.id = id;
    }
    public APISource getSource() {
        return source;
    }

    public void setSource(APISource source) {
        this.source = source;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getSourceVersion() {
        return sourceVersion;
    }

    public void setSourceVersion(String sourceVersion) {
        this.sourceVersion = sourceVersion;
    }
        
    
}
