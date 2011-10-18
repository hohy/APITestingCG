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
