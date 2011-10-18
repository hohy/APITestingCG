package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
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
    
    
}
