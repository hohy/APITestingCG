package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jan Hýbl
 */
public class APIField extends APIItem implements Comparable<APIField> {
    private String varType;

    public APIField(String varType, String name, Set<Modifier> modifiers) {
        this.varType = varType;
        this.modifiers = modifiers;
        this.name = name;
        this.kind = Kind.VARIABLE;
    }    
    
    public APIField(JCVariableDecl jcvd, Map<String, String> importsMap) {
        this.name = jcvd.name.toString();
        this.varType = findFullClassName(jcvd.vartype.toString(), importsMap);
        this.modifiers = APIModifier.getModifiersSet(jcvd.getModifiers().getFlags());
        this.kind = getKind(jcvd.getKind());
    }

    public APIField(Field f) {
        this.name = f.getName();
        this.varType = f.getType().getSimpleName();
        this.modifiers = APIModifier.getModifiersSet(f.getModifiers());
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final APIField other = (APIField) obj;
        
        return this.name.equals(other.name) && this.kind.equals(other.kind) && this.modifiers.equals(other.modifiers) && this.varType.equals(other.varType);
    }

}
