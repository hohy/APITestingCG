package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import com.sun.tools.javac.tree.JCTree;

/**
 * Class represents one parameter of method or constructor.
 */
public class APIMethodParameter {

    /**
     * Name of parameter
     */
    private String name;

    /**
     * Full name of class.
     */
    private String type;

    public APIMethodParameter(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public APIMethodParameter(String name, JCTree.JCVariableDecl jcvd) {
        if (name == null) {
            this.name = jcvd.name.toString();
        } else {
            this.name = name;
        }
        type = jcvd.type.toString();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof APIMethodParameter)) return false;

        APIMethodParameter that = (APIMethodParameter) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
