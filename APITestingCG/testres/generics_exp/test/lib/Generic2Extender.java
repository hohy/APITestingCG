
package test.lib;

import lib.Generic2;

public class Generic2Extender<T, S> extends Generic2<T, S> {


    public Generic2Extender(T t, S s) {
        super(t, s);
    }

    @Override
    public T getValue1() {
        throw new UnsupportedOperationException();
    }

    @Override
    public S getValue2() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue1(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue2(S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValues(T t, S s) {
        throw new UnsupportedOperationException();
    }

    public void fields() {
        S sValue = null;
        s = sValue;
        T tValue = null;
        t = tValue;
    }

}
