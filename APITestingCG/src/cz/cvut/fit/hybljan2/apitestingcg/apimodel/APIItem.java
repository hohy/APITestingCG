package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Modifier;

/**
 *
 * @author Jan Hýbl
 */
public abstract class APIItem {
    protected String name;
    protected Set<Modifier> modifiers;
    /**
     * TODO: mozna, kdyz uz si delam svuj model API, tak bych si mel udělat 
     * i vlastni ENUM pro Kind a nespolehat se na ten od Sunu... ale zase podle
     * javadocu je tohle normálně veřejné API, ne jak ty věci z com.sun.tools.javac...
     * takže bych to použít mohl...
     */ 
    protected Kind kind;
    
    public String getName() {
        return name;
    }
    
    public Set<Modifier> getModifiers() {
        return modifiers;
    }

    public Kind getType() {
        return kind;
    }
    
    protected String getClassName(Class c) {
        return c.getName();
    }
    
    protected Set<Modifier> getModifiersSet(int modifiers) {
        Set<Modifier> result = new HashSet<Modifier>();
        if(java.lang.reflect.Modifier.isAbstract(modifiers)) result.add(Modifier.ABSTRACT);
        if(java.lang.reflect.Modifier.isFinal(modifiers)) result.add(Modifier.FINAL);
//        if(java.lang.reflect.Modifier.isInterface(modifiers)) result.add(Modifier.);        
        if(java.lang.reflect.Modifier.isNative(modifiers)) result.add(Modifier.NATIVE);
        if(java.lang.reflect.Modifier.isPrivate(modifiers)) result.add(Modifier.PRIVATE);
        if(java.lang.reflect.Modifier.isProtected(modifiers)) result.add(Modifier.PROTECTED);
        if(java.lang.reflect.Modifier.isPublic(modifiers)) result.add(Modifier.PUBLIC);
        if(java.lang.reflect.Modifier.isStatic(modifiers)) result.add(Modifier.STATIC);
        if(java.lang.reflect.Modifier.isStrict(modifiers)) result.add(Modifier.STRICTFP);
        if(java.lang.reflect.Modifier.isSynchronized(modifiers)) result.add(Modifier.SYNCHRONIZED);
        if(java.lang.reflect.Modifier.isTransient(modifiers)) result.add(Modifier.TRANSIENT);
        if(java.lang.reflect.Modifier.isVolatile(modifiers)) result.add(Modifier.VOLATILE);
        return result;
    }
    
    protected String getFullClassName(String simpleName, Map<String, String> importsMap) {
        // if class name doesn't contains dot, 
        // it's not full class name with package name
        // have to try to add it.
        if(!simpleName.contains(".")) { 
            if(importsMap.containsKey(simpleName)) {
                return importsMap.get(simpleName);                
            }            
        }
        return simpleName;
    }
    
}
