
package test.lib;

import java.util.List;
import lib.Generic10;

public class Generic10Extender<T extends List<List<List<T>>>> extends Generic10<T> {


    public Generic10Extender(T t) {
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
