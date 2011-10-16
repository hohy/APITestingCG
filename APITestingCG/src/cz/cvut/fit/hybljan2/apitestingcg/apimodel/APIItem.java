package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.Set;
import javax.lang.model.element.Modifier;

/**
 *
 * @author Jan Hýbl
 */
public abstract class APIItem {
    protected String name;
    protected Set<Modifier> modifiers;

    public String getName() {
        return name;
    }
    
    public Set<Modifier> getModifiers() {
        return modifiers;
    }      
}
