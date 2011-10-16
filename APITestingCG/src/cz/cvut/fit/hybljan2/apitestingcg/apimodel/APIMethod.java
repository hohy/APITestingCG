package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.List;

/**
 * Represents method.
 * @author Jan Hýbl
 */
public class APIMethod {
    private String name;
    private List<String> parameters;

    public APIMethod(String name) {
        this.name = name;
    }
        
}
