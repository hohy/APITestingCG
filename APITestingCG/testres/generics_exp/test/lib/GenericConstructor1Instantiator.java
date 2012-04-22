
package test.lib;

import java.util.Collection;
import lib.GenericConstructor1;

public class GenericConstructor1Instantiator {


    public <T> GenericConstructor1 createGenericConstructor1(T t) {
        return new GenericConstructor1(t);
    }

    public <T> GenericConstructor1 createGenericConstructor1(Collection<T> tt) {
        return new GenericConstructor1(tt);
    }

    public <T> GenericConstructor1 createGenericConstructor1() {
        return new GenericConstructor1();
    }

}
