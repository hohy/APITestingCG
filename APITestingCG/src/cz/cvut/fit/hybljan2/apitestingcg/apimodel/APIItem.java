package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jan Hýbl
 */
public abstract class APIItem {
    protected String name;
    protected List<Modifier> modifiers;
    /**
     * TODO: Poutřebuju vůbec kind? bych řekl že skoro ne... by se to dalo 
     * udelat jako normalni tridy... už to tak v podstate mam jen chybí intefacy
     * enumy, anotace... ale to by byly jen potomci APIClass.
     */ 
    protected Kind kind;
    
    public String getName() {
        return name;
    }
    
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public Kind getType() {
        return kind;
    }
    
    /**
     * Method adds to class simple name package name. Example: File -> java.io.File
     * @param simpleName    Simple class name - without package name
     * @param importsMap    Maps simple names to full names.
     * @return  fulll class name 
     */
    protected String findFullClassName(String simpleName, Map<String, String> importsMap) {
        // if class name doesn't contains dot, 
        // it's not full class name with package name
        // have to try to add it.
        if(!simpleName.contains(".")) {
            // if class is generics, we have to get full name for both classes
            if(!simpleName.contains("<")) {                       
                if(importsMap.containsKey(simpleName)) {
                    return importsMap.get(simpleName);                
                }
            } else {
                String firstName = simpleName.substring(0, simpleName.indexOf('<'));
                String secondName = simpleName.substring(simpleName.indexOf('<')+1, simpleName.length()-1);
                if(importsMap.containsKey(firstName)) firstName = importsMap.get(firstName);
                if(importsMap.containsKey(secondName)) secondName = importsMap.get(secondName);
                return firstName + '<' + secondName + '>';
            }
        }
        return simpleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final APIItem other = (APIItem) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.modifiers != other.modifiers && (this.modifiers == null || !this.modifiers.equals(other.modifiers))) {
            return false;
        }
        if (this.kind != other.kind) {
            return false;
        }
        return true;
    }

    protected Kind getKind(com.sun.tools.javac.tree.JCTree.JCClassDecl.Kind kind) {
        switch(kind) {
            case CLASS: return Kind.CLASS;
            case METHOD: return Kind.METHOD;
            case INTERFACE: return Kind.INTERFACE;
            case ANNOTATION: return Kind.ANNOTATION;
            case ENUM: return Kind.ENUM;
            case VARIABLE: return Kind.VARIABLE;
        }
        return null;
    }    
    
    public enum Kind {
        CLASS, METHOD, INTERFACE, ANNOTATION, ENUM, VARIABLE, PACKAGE
    }

}
