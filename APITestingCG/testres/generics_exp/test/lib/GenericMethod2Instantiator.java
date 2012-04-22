
package test.lib;

import java.io.Serializable;
import java.util.List;
import lib.GenericMethod2;

public class GenericMethod2Instantiator {


    public GenericMethod2 createGenericMethod2() {
        return new GenericMethod2();
    }

    public <T, S> T getValue1(GenericMethod2 instance, T t, S s) {
        return instance.getValue1(t, s);
    }

    public <T, S> T getValue1NullCall(GenericMethod2 instance, T t, S s) {
        return instance.getValue1(null, null);
    }

    public <T extends Number & Runnable, S extends Serializable> T getValue2(GenericMethod2 instance, T t, S s) {
        return instance.getValue2(t, s);
    }

    public <T extends Number & Runnable, S extends Serializable> T getValue2NullCall(GenericMethod2 instance, T t, S s) {
        return instance.getValue2(null, null);
    }

    public <T extends Number & Runnable & Serializable, S extends List & Cloneable> T getValue3(GenericMethod2 instance, T t, S s) {
        return instance.getValue3(t, s);
    }

    public <T extends Number & Runnable & Serializable, S extends List & Cloneable> T getValue3NullCall(GenericMethod2 instance, T t, S s) {
        return instance.getValue3(null, null);
    }

}
