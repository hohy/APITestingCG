package test.lib;

import lib.Generic1;

public class Generic1Extender<T> extends Generic1<T> {


    public Generic1Extender(T t) {
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
