
package test.lib;

import java.util.List;
import java.util.Set;
import lib.Generic12;

public class Generic12Extender<T extends List<T> & Set<T>> extends Generic12<T> {


    public Generic12Extender(T t) {
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
