package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.List;

/**
 * Represent java class. Store information about it. Contains list of class
 * methods.
 * @author hohy
 */
public class APIClass {
    private String name;
    private String modifiers;
    private List<APIMethod> methods;

    public APIClass(String name) {
        this.name = name;
    }
    
    public void addMethod(APIMethod method) {
        methods.add(method);
    }
}
