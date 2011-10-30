package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.util.Map;
import java.util.Set;

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
    
    protected String findFullClassName(String simpleName, Map<String, String> importsMap) {
        // if class name doesn't contains dot, 
        // it's not full class name with package name
        // have to try to add it.
        if(!simpleName.contains(".")) {
            // if class is generics, we have to get full name for both classes
            if(!simpleName.contains("<")) {                       
                if(importsMap.containsKey(simpleName)) {
                    return importsMap.get(simpleName);                
                }
            } else {
                String firstName = simpleName.substring(0, simpleName.indexOf('<'));
                String secondName = simpleName.substring(simpleName.indexOf('<')+1, simpleName.length()-1);
                if(importsMap.containsKey(firstName)) firstName = importsMap.get(firstName);
                if(importsMap.containsKey(secondName)) secondName = importsMap.get(secondName);
                return firstName + '<' + secondName + '>';
            }
        }
        return simpleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final APIItem other = (APIItem) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.modifiers != other.modifiers && (this.modifiers == null || !this.modifiers.equals(other.modifiers))) {
            return false;
        }
        if (this.kind != other.kind) {
            return false;
        }
        return true;
    }
}
