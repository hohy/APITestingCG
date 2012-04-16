
package test.lib;

import lib.Generic3;

public class Generic3Extender<T extends Number> extends Generic3<T> {


    public Generic3Extender(T t) {
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
