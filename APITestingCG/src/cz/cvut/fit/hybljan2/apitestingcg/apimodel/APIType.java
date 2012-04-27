package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents type in API.
 * User: Jan Hýbl
 * Date: 27.4.12
 * Time: 13:43
 */
public class APIType {

    /**
     * Name of the class (with package name),
     * Examples: java.util.List, java.io.File
     */
    private String name;

    /**
     * List of the type arguments of a type.
     * Example: For type <code>java.util.Map<java.lang.Integer, java.lang.String></code>
     * typeArgs list contains two items: <code>java.lang.Integer</code> and <code>java.lang,String</code>.
     */
    private List<APIType> typeArgs = new LinkedList<>();

    /**
     * Indicates if the type is array.
     */
    private boolean array = false;

    public APIType(String name) {
        this.name = name;
    }

    public APIType(String name, boolean array) {
        this.name = name;
        this.array = array;
    }

    public void addTypeParameter(APIType param) {
        getTypeArgs().add(param);
    }

    public String getName() {
        return name;
    }

    public List<APIType> getTypeArgs() {
        return typeArgs;
    }

    public boolean isArray() {
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getName());
        if(!getTypeArgs().isEmpty()) {
            sb.append('<');
            for(Iterator<APIType> it = getTypeArgs().iterator(); it.hasNext(); ) {
                sb.append(it.next().toString());
                if(it.hasNext()) {
                    sb.append(", ");
                }
            }
            sb.append('>');
        }

        if(isArray()) {
            sb.append("[]");
        }

        return sb.toString();
    }
}
