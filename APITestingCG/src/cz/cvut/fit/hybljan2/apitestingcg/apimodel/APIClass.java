package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCModifiers;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
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
    
    public APIClass(Class cls) {
        this.name = cls.getSimpleName();
        this.methods = new LinkedList<APIMethod>();  
        for(Constructor constr : cls.getDeclaredConstructors()) {
            APIMethod apimth = new APIMethod(constr);
            if(apimth.getModifiers().contains(Modifier.PUBLIC) 
                    || apimth.getModifiers().contains(Modifier.PROTECTED)) 
                this.methods.add(apimth);
        }
        for(Method mth : cls.getDeclaredMethods()) {
            APIMethod apimth = new APIMethod(mth);
            if(apimth.getModifiers().contains(Modifier.PUBLIC) 
                    || apimth.getModifiers().contains(Modifier.PROTECTED)) 
                this.methods.add(apimth);
        }
        this.modifiers = getModifiersSet(cls.getModifiers());
        this.fields = new HashSet<APIField>();
        for(Field f : cls.getDeclaredFields()) {
            APIField apifield = new APIField(f);
            if(apifield.getModifiers().contains(Modifier.PUBLIC) 
                    || apifield.getModifiers().contains(Modifier.PROTECTED))
                this.fields.add(new APIField(f));
        }
        this.kind = getKind(cls);
        if(cls.getSuperclass() != null) this.extending = cls.getSuperclass().getSimpleName();
        this.implementing = new LinkedList<String>();
        for(Class intfc : cls.getInterfaces()) {
            this.implementing.add(intfc.getSimpleName());
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

    private Kind getKind(Class cls) {
        if(cls.isAnnotation()) return Kind.ANNOTATION;
        if(cls.isInterface()) return Kind.INTERFACE;
        if(cls.isEnum()) return Kind.ENUM;        
        return Kind.CLASS;
    }
}
