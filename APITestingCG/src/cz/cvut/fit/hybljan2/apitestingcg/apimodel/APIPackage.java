package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents package of classes in API.
 * @author Jan Hýbl
 */
public class APIPackage extends APIItem{
    
    private List<APIClass> classes;

    public APIPackage(String name) {
        this.name = name;
        classes = new LinkedList<APIClass>();
        this.kind = Kind.PACKAGE;
    }
    
    public void addClass(APIClass clazz) {
        classes.add(clazz);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\npackage ").append(name).append("\n\n");
        for(APIClass c : classes) sb.append(c).append('\n');
        return sb.toString();
    }

    public List<APIClass> getClasses() {
        return classes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final APIPackage other = (APIPackage) obj;
        if (this.classes != other.classes && (this.classes == null || !this.classes.equals(other.classes))) {
            return false;
        }
        return true;
    }
    
}
