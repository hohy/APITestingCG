package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.code.Type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
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

    private APIModifier accessModifier = APIModifier.UNSET;

    public APIType(String name) {
        this.name = name;
    }

    public APIType(String name, boolean array) {
        this.name = name;
        this.array = array;
    }

    public APIType(Type type) {
        this.name = type.tsym.getQualifiedName().toString();
        if(type instanceof Type.ArrayType) {
            this.name = ((Type.ArrayType) type).elemtype.tsym.getQualifiedName().toString();
            this.array = true;
        }
        for(Type typeParam : type.getTypeArguments()) {
            addTypeParameter(new APIType(typeParam));
        }
    }

    // TODO: poradne implementovat a otestovat nasledujici konstruktory... tohle je jen takova nouzovka...
    public APIType(java.lang.reflect.Type type) {
        this.name = type.toString();
    }
    
    public APIType(Class type) {
        this.name = type.getName();
    }

    public APIType(GenericArrayType type) {

    }

    public APIType(ParameterizedType type) {

    }

    public APIType(TypeVariable type) {
        this.name = type.getName();
    }

    public APIType(WildcardType type) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof APIType)) return false;

        APIType apiType = (APIType) o;

        if (array != apiType.array) return false;
        if (!name.equals(apiType.name)) return false;
        if (typeArgs != null ? !typeArgs.equals(apiType.typeArgs) : apiType.typeArgs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (typeArgs != null ? typeArgs.hashCode() : 0);
        result = 31 * result + (array ? 1 : 0);
        return result;
    }

    public String getSimpleName() {
        return getName().substring(0, getName().lastIndexOf("."));
    }

    public static final APIType voidType = new APIType("void");
}
