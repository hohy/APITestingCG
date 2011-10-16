package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import java.util.Set;
import javax.lang.model.element.Modifier;

/**
 *
 * @author Jan Hýbl
 */
public class APIField {
    private String name;
    private String type;
    private Set<Modifier> modifiers;

    public APIField(JCVariableDecl jcvd) {
        this.name = jcvd.name.toString();
        this.type = jcvd.vartype.toString();
        this.modifiers = jcvd.getModifiers().getFlags();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Modifier m : modifiers) sb.append(m).append(' ');
        sb.append(type).append(' ');
        sb.append(name);
        return sb.toString();
    }        
}
