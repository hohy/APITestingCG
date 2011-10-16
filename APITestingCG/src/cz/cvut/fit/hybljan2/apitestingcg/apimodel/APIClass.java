package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCModifiers;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;

/**
 * Represent java class. Store information about it. Contains list of class
 * methods.
 * @author hohy
 */
public class APIClass extends APIItem {
    private Set<Modifier> modifiers;
    private List<APIMethod> methods;
    private Set<APIField> fields;

    public APIClass(String name) {
        this.name = name;
        methods = new LinkedList<APIMethod>();
        fields = new HashSet<APIField>();
    }

    public APIClass(JCClassDecl jccd) {
        this.name = jccd.name.toString();
        this.methods = new LinkedList<APIMethod>();        
        this.modifiers = jccd.mods.getFlags();
        this.fields = new HashSet<APIField>();
    }
    
    public void addMethod(APIMethod method) {
        methods.add(method);
    }
    
    public void addField(APIField field) {
        fields.add(field);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Class ").append(name).append(" mods: ").append(modifiers).append('\n');
        for(APIMethod m : methods) sb.append("      ").append(m).append('\n');
        return sb.toString();
    }

    public Set<APIField> getFields() {
        return fields;
    }

    public List<APIMethod> getMethods() {
        return methods;
    }

    public Set<Modifier> getModifiers() {
        return modifiers;
    }          
}
