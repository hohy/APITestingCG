
package test.lib;

import java.util.List;
import java.util.Set;
import lib.Generic12;

public class Generic12Instantiator {


    public <T extends List<T> & Set<T>> Generic12<T> createGeneric12(T t) {
        return new Generic12<T>(t);
    }

    public <T extends List<T> & Set<T>> T getValue(Generic12<T> instance) {
        return instance.getValue();
    }

    public <T extends List<T> & Set<T>> void setValue(Generic12<T> instance, T t) {
        instance.setValue(t);
    }

    public <T extends List<T> & Set<T>> void setValueNullCall(Generic12<T> instance, T t) {
        instance.setValue(null);
    }

    public <T extends List<T> & Set<T>> void fields(Generic12<T> instance) {
        T tValue = null;
        instance.t = tValue;
    }

}
