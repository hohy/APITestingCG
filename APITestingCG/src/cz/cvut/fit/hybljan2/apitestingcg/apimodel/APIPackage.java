package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.List;

/**
 * Class that represents package of classes in API.
 * @author Jan Hýbl
 */
public class APIPackage {
    
    private String name;
    private List<APIClass> classes;

    public APIPackage(String name) {
        this.name = name;
    }
    
    public void addClass(APIClass clazz) {
        classes.add(clazz);
    }
}
