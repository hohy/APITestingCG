
package test.lib;

import java.util.List;
import lib.GenericAncestor;

public class GenericAncestorExtender extends GenericAncestor {


    public GenericAncestorExtender() {
        super();
    }

    @Override
    public void someMethod(List a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(GenericAncestor a) {
        throw new UnsupportedOperationException();
    }

}
