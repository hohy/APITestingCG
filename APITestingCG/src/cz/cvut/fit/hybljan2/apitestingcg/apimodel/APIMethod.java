package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents method.
 * @author Jan Hýbl
 */
public class APIMethod extends APIItem {

    private List<String> parameters;
    private String returnType;
    private SortedSet<String> thrown;
    
    public APIMethod(String name, List<Modifier> modifiers, List<String> params, String returnType, SortedSet<String> thrown) {
        this.name = name;
        this.kind = Kind.METHOD;
        this.modifiers = modifiers;
        this.parameters = params;
        this.returnType = returnType;
        this.thrown = thrown;
    }

    public APIMethod(JCMethodDecl jcmd, Map<String, String> importsMap) {
        this.name = jcmd.name.toString();
        this.modifiers = APIModifier.getModifiersSet(jcmd.getModifiers().getFlags());
        
        this.thrown = new TreeSet<String>();
        if(jcmd.getThrows() != null) {
            for(JCExpression e : jcmd.getThrows()) 
                this.thrown.add(findFullClassName(e.toString(), importsMap));
        }
        
        if(jcmd.getReturnType() == null) this.returnType = "void";
        else this.returnType = findFullClassName(jcmd.getReturnType().toString(), importsMap);
                
        this.parameters = new LinkedList<String>();
        for(JCVariableDecl jcvd : jcmd.getParameters()) 
            parameters.add(findFullClassName(jcvd.getType().toString(), importsMap));
        this.kind = getKind(jcmd.getKind());
    }

    public APIMethod(Method mth) {
        this.name = mth.getName();
        this.modifiers = APIModifier.getModifiersSet(mth.getModifiers());
        this.thrown = new TreeSet<String>();
        for(java.lang.reflect.Type excType : mth.getExceptionTypes()) {
            this.thrown.add(excType.toString().substring(6));  // TODO: toto by chtělo asi udělat nějak elegantnějš...            
        }
        this.parameters = new LinkedList<String>();
        for(Class c : mth.getParameterTypes()) {
            this.parameters.add(c.getName());
        }        
        this.returnType = mth.getReturnType().getSimpleName();
        this.kind = Kind.METHOD;
    }
    
    public APIMethod(Constructor c) {
        this.name = c.getName();
        this.modifiers = APIModifier.getModifiersSet(c.getModifiers());
        this.thrown = new TreeSet<String>();
        for(java.lang.reflect.Type excType : c.getExceptionTypes()) {
            this.thrown.add(excType.toString().substring(6));  // TODO: toto by chtělo asi udělat nějak elegantnějš...            
        }
        this.parameters = new LinkedList<String>();
        for(Class paramc : c.getParameterTypes()) {
            this.parameters.add(c.getName());
        }        
        this.returnType = null;
        this.kind = Kind.METHOD;        
    }
    
    /**
     * Return string representation of APIMethod.
     * Format is [Modifiers] method [Name] [ReturnType] ([Parameteres])
     * Example: public static doSomething Object (int, double)
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Modifier m : modifiers) sb.append(m).append(' ');                
        if(returnType != null) sb.append("method ").append(returnType).append(' ');
        else sb.append("constructor ");        
        sb.append(name).append('(');
        for(String f : parameters) sb.append(f).append(',');
        if(parameters.size() > 0) sb.deleteCharAt(sb.length()-1);
        sb.append(')');
        if(thrown != null && thrown.size() > 0) {
            sb.append(" throws");
            for(String ex : thrown) {
                sb.append(" ").append(ex);
            }
        }
        return sb.toString();
    }

    public List<String> getParameters() {
        return parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public SortedSet<String> getThrown() {
        return thrown;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final APIMethod other = (APIMethod) obj;
        if (this.parameters != other.parameters && (this.parameters == null || !this.parameters.equals(other.parameters))) {
            return false;
        }
        if ((this.returnType == null) ? (other.returnType != null) : !this.returnType.equals(other.returnType)) {
            return false;
        }
        if (this.thrown != other.thrown && (this.thrown == null || !this.thrown.equals(other.thrown))) {
            return false;
        }
        if(!super.equals(obj)) return false;        
        return true;
    }
}
