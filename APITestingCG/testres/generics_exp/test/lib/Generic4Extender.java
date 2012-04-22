
package test.lib;

import lib.Generic4;

public class Generic4Extender<T extends Number & Runnable> extends Generic4<T> {


    public Generic4Extender(T t) {
        super(t);
    }

    @Override
    public T getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(T t) {
        throw new UnsupportedOperationException();
    }

    public void fields() {
        T tValue = null;
        t = tValue;
    }

}
