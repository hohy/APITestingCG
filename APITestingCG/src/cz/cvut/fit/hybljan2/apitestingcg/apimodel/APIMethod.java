package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.lang.model.element.Modifier;

/**
 * Represents method.
 * @author Jan Hýbl
 */
public class APIMethod {
    private String name;
    private Set<Modifier> modifiers;
    private List<APIField> parameters;
    private String returnType;

    public APIMethod(String name) {
        this.name = name;
    }

    public APIMethod(JCMethodDecl jcmd) {
        this.name = jcmd.name.toString();
        this.modifiers = jcmd.getModifiers().getFlags();
        this.parameters = new LinkedList<APIField>();
        if(jcmd.getReturnType() == null) this.returnType = "void";
        else this.returnType = jcmd.getReturnType().toString();
        for(JCVariableDecl jcvd : jcmd.getParameters()) parameters.add(new APIField(jcvd));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Modifier m : modifiers) sb.append(m).append(' ');        
        sb.append(" method ").append(returnType).append(' ').append(name).append('(');
        for(APIField f : parameters) sb.append(f).append(',');
        if(parameters.size() > 0) sb.deleteCharAt(sb.length()-1);
        sb.append(')');
        return sb.toString();
    }    
}
