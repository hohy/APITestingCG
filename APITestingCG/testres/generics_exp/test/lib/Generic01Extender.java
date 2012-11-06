
package test.lib;

import lib.Generic01;

public class Generic01Extender<T> extends Generic01<T> {


    public Generic01Extender(T t) {
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
