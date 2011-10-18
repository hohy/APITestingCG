package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
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

    private List<APIMethod> methods;
    private Set<APIField> fields;
    private String extending;
    private List<String> implementing;

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
        this.kind = jccd.getKind();
        if(jccd.getExtendsClause() != null) this.extending = jccd.getExtendsClause().getTree().toString();
        if(jccd.getImplementsClause() != null) {
            this.implementing = new LinkedList<String>();
            for(JCExpression e : jccd.getImplementsClause()) this.implementing.add(e.toString());
        }
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

    public String getExtending() {
        return extending;
    }

    public List<String> getImplementing() {
        return implementing;
    }
}
