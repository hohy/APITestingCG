package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree.JCVariableDecl;

import java.lang.reflect.Field;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.List;

/**
 * @author Jan Hýbl
 */
public class APIField extends APIItem implements Comparable<APIField> {
    private APIType varType;

    /**
     * Constructor used in unit testing.
     *
     * @param varType
     * @param name
     * @param modifiers
     */
    public APIField(String varType, String name, List<APIModifier> modifiers) {
        this.varType = new APIType(varType);
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
        this.varType = new APIType(jcvd.type);
        this.modifiers = APIModifier.getModifiersSet(jcvd.getModifiers().getFlags());
        this.kind = getKind(jcvd.getKind());

    }

    public APIField(JCVariableDecl jcvd, boolean constant) {
        this.name = jcvd.name.toString();
        this.varType = new APIType(jcvd.type);
        this.modifiers = APIModifier.getModifiersSet(jcvd.getModifiers().getFlags());
        if (constant) {
            modifiers.add(APIModifier.PUBLIC);
            modifiers.add(APIModifier.FINAL);
            modifiers.add(APIModifier.STATIC);
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
        this.varType = new APIType(f.getGenericType());
        this.modifiers = APIModifier.getModifiersSet(f.getModifiers());
        this.kind = Kind.VARIABLE;
        if (f.getAnnotation(java.lang.Deprecated.class) != null) {
            setDepreacated(true);
        }
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
        if (isDepreacated()) {
            sb.append("Deprecated").append(' ');
        }
        for (APIModifier m : modifiers) sb.append(m).append(' ');
        sb.append(varType).append(' ');
        sb.append(name);
        return sb.toString();
    }

    public APIType getVarType() {
        return varType;
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
