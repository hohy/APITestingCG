package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.org.apache.regexp.internal.REUtil;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hohy
 */
public class MethodGenerator {
    // method name
    private String name;    
    // method modifiers
    private String modifiers;
    // list of params - Strign array {type, name}
    private List<String[]> params;
    private String returnType;
    private List<String> thrown;
    private List<String> annotations = new LinkedList<String>();    
    // List for class generator. Contains classes used in method, 
    // that has to be imported in class with this method.
    private Set<String> imports = new HashSet<String>();
    // body
    private String body;
    
    public String generateMethod() {
        StringBuilder sb = new StringBuilder();
        
        // anotation
        for(String annotation : annotations) {
            sb.append("\t@").append(annotation).append('\n');
        }
        // method header
        sb.append('\t').append(modifiers).append(' ');
        if(returnType != null && returnType.length() > 0) sb.append(returnType).append(' ');
        sb.append(name).append('(');
        if(params != null && params.size() > 0) {            
            for (String[] param : params) {
                sb.append(param[0]).append(' ').append(param[1]).append(", ");
            }
            // remove last ", "
            sb.delete(sb.length()-2, sb.length());
        }
        sb.append(") ");
        if(thrown != null && thrown.size() > 0) {
            sb.append("throws ");
            for (String exc : thrown) {
                sb.append(exc).append(", ");
            }
            sb.delete(sb.length()-2, sb.length());
        }
        sb.append("{\n");
        
        // method body
        sb.append(body);
        // method end
        sb.append("\n\t}");
        return sb.toString();
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }   
    
    public void setName(String name) {
        this.name = name;
    }

    public void setParams(List<String[]> params) {
        this.params = params;
        for (String[] strings : this.params) {
            strings[0] = getClassName(strings[0]);
        }
        
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = getClassName(returnType);
    }

    public List<String> getThrown() {
        return thrown;
    }

    public void setThrown(List<String> thrown) {
        this.thrown = thrown;
    }

    void setBody(String body) {
        this.body = body;
    }

    public Set<String> getImports() {
        return imports;
    }
    
    /**
     * Convert full name of class to simple name and add it to imports.
     * Example: java.io.File -> File
     * @param rawName 
     */
    private String getClassName(String rawName) {
        if(!rawName.contains(".")) { // it's simple name, return it.
            return rawName;
        } else {
            imports.add(rawName);
            return rawName.substring(rawName.lastIndexOf(".")+1);
        }
    }

    public List<String[]> getParams() {
        return params;
    }

    public void addAnotation(String anotation) {
        this.annotations.add(anotation);
    }
    
}
