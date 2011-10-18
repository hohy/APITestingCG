package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import java.util.Set;
import javax.lang.model.element.Modifier;

/**
 *
 * @author Jan Hýbl
 */
public class APIField extends APIItem{
    private String varType;

    public APIField(JCVariableDecl jcvd) {
        this.name = jcvd.name.toString();
        this.varType = jcvd.vartype.toString();
        this.modifiers = jcvd.getModifiers().getFlags();
        this.kind = jcvd.getKind();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Modifier m : modifiers) sb.append(m).append(' ');
        sb.append(varType).append(' ');
        sb.append(name);
        return sb.toString();
    }        
}
