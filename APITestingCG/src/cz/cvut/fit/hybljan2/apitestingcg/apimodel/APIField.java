package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Jan Hýbl
 */
public class APIField extends APIItem implements Comparable<APIField> {
    private String varType;

    /**
     * Constructor used in unit testing.
     *
     * @param varType
     * @param name
     * @param modifiers
     */
    public APIField(String varType, String name, List<Modifier> modifiers) {
        this.varType = varType;
        this.modifiers = modifiers;
        this.name = name;
        this.kind = Kind.VARIABLE;
    }

    /**
     * Constructor used by source scanner.
     *
     * @param jcvd
     */
    public APIField(JCVariableDecl jcvd) {
        this.name = jcvd.name.toString();
        this.varType = jcvd.type.toString();
        this.modifiers = APIModifier.getModifiersSet(jcvd.getModifiers().getFlags());
        this.kind = getKind(jcvd.getKind());

    }

    public APIField(JCVariableDecl jcvd, boolean constant) {
        this.name = jcvd.name.toString();
        this.varType = jcvd.type.toString();//.tsym.flatName().toString();
        this.modifiers = APIModifier.getModifiersSet(jcvd.getModifiers().getFlags());
        if (constant) {
            modifiers.add(Modifier.PUBLIC);
            modifiers.add(Modifier.FINAL);
            modifiers.add(Modifier.STATIC);
        }
        this.kind = getKind(jcvd.getKind());

    }

    /**
     * Constructor used by bytecode scanner.
     *
     * @param f
     */
    public APIField(Field f) {
        this.name = f.getName();
        this.varType = getTypeName(f.getGenericType());
        this.modifiers = APIModifier.getModifiersSet(f.getModifiers());
        this.kind = Kind.VARIABLE;
    }

    /**
     * Return string representation of APIField.
     * Format is [Modifiers] [Type] [Name]
     * Example: public static final int numberVar
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Modifier m : modifiers) sb.append(m).append(' ');
        sb.append(varType).append(' ');
        sb.append(name);
        return sb.toString();
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    /**
     * APIField are compared by names,
     * so in SortedSet will be sorted in alphabetic order.
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(APIField t) {
        return name.compareTo(t.name);
    }

    @Override
    public void accept(IAPIVisitor visitor) {
        visitor.visit(this);
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
        return super.equals(obj) && this.varType.equals(other.varType);
    }

}
