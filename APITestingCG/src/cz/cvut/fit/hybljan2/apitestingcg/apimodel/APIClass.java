package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represent java class. Store information about it. Contains list of class
 * methods.
 * @author hohy
 */
public class APIClass extends APIItem {

    private List<APIMethod> methods;
    private SortedSet<APIField> fields;
    private String extending;
    private List<String> implementing;
    // Full name of class (contains package name) - expample: java.util.Set
    private String fullName;

    public APIClass(String name) {
        this.name = name;
        methods = new LinkedList<APIMethod>();
        fields = new TreeSet<APIField>();
    }

    public APIClass(JCClassDecl jccd, String packageName, Map<String, String> importsMap) {
        this.name = jccd.name.toString();
        this.fullName = packageName + '.' + jccd.name.toString();
        this.methods = new LinkedList<APIMethod>();        
        this.modifiers = APIModifier.getModifiersSet(jccd.mods.getFlags());
        this.fields = new TreeSet<APIField>();
        this.kind = getKind(jccd.getKind());
        if(jccd.getExtendsClause() != null) this.extending = findFullClassName(jccd.getExtendsClause().getTree().toString(), importsMap);
        if(jccd.getImplementsClause() != null) {
            this.implementing = new LinkedList<String>();
            for(JCExpression e : jccd.getImplementsClause()) this.implementing.add(findFullClassName(e.toString(), importsMap));
        }
    }
    
    public APIClass(Class cls) {
        this.name = cls.getSimpleName();
        this.fullName = cls.getName();
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
        this.modifiers = APIModifier.getModifiersSet(cls.getModifiers());
        this.fields = new TreeSet<APIField>();
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

    /**
     * Converts this class to String.
     * String format: [modifiers] [kind] [fullName] extends [extending] implements [implementing]\n
     * [fields] [methods]
     * @return  String representation of this class 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();        
        if(modifiers != null && modifiers.size() > 0) {            
            for(Modifier m : modifiers) sb.append(m).append(' ');
        }
        sb.append(kind.toString().toLowerCase()).append(' ');
        sb.append(fullName);
        if(extending != null) sb.append(" extends ").append(extending);
        if(implementing != null && implementing.size() > 0) {
            sb.append(" implements");
            for(String i : implementing) sb.append(' ').append(i);
        }
        if(fields != null) for(APIField f : fields) sb.append("\n ").append(f.toString());
        if(methods != null) for(APIMethod m : methods) sb.append("\n ").append(m.toString());
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

    public String getFullName() {
        return fullName;
    }

    private Kind getKind(Class cls) {
        if(cls.isAnnotation()) return Kind.ANNOTATION;
        if(cls.isInterface()) return Kind.INTERFACE;
        if(cls.isEnum()) return Kind.ENUM;        
        return Kind.CLASS;
    }
}
