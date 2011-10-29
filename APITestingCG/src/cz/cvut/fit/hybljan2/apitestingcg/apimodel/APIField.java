package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import java.lang.reflect.Field;
import java.util.Map;
import javax.lang.model.element.Modifier;

/**
 *
 * @author Jan Hýbl
 */
public class APIField extends APIItem implements Comparable<APIField> {
    private String varType;

    public APIField(JCVariableDecl jcvd, Map<String, String> importsMap) {
        this.name = jcvd.name.toString();
        this.varType = getFullClassName(jcvd.vartype.toString(), importsMap);
        this.modifiers = jcvd.getModifiers().getFlags();
        this.kind = jcvd.getKind();
    }

    public APIField(Field f) {
        this.name = f.getName();
        this.varType = f.getType().getSimpleName();
        this.modifiers = getModifiersSet(f.getModifiers());
        this.kind = Kind.VARIABLE;
    }
        
    /**
     * Return string representation of APIField.
     * Format is [Modifiers] [Type] [Name]
     * Example: public static final int numberVar
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Modifier m : modifiers) sb.append(m).append(' ');
        sb.append(varType).append(' ');
        sb.append(name);
        return sb.toString();
    }

    /**
     * APIField are compared by names, 
     * so in SortedSet will be sorted in alphabetic order.
     * @param t
     * @return 
     */
    @Override
    public int compareTo(APIField t) {
        return name.compareTo(t.name);
    }
}
