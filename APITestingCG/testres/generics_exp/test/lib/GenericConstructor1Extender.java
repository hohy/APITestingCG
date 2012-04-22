
package test.lib;

import java.util.Collection;
import lib.GenericConstructor1;

public class GenericConstructor1Extender extends GenericConstructor1 {


    public <T> GenericConstructor1Extender(T t) {
        super(t);
    }

    public <T> GenericConstructor1Extender(Collection tt) {
        super(tt);
    }

    public <T> GenericConstructor1Extender() {
        super();
    }

}
