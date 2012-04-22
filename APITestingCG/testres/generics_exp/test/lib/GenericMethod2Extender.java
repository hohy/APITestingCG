
package test.lib;

import java.io.Serializable;
import java.util.List;
import lib.GenericMethod2;

public class GenericMethod2Extender extends GenericMethod2 {


    public GenericMethod2Extender() {
        super();
    }

    @Override
    public <T, S> T getValue1(T t, S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Number & Runnable, S extends Serializable> T getValue2(T t, S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Number & Runnable & Serializable, S extends List & Cloneable> T getValue3(T t, S s) {
        throw new UnsupportedOperationException();
    }

}
