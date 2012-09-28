
package test.lib;

import java.util.List;
import lib.Generic10;

public class Generic10Instantiator {


    public <T extends List<List<List<T>>>> Generic10<T> createGeneric10(T t) {
        return new Generic10<T>(t);
    }

    public <T extends List<List<List<T>>>> T getValue(Generic10<T> instance) {
        return instance.getValue();
    }

    public <T extends List<List<List<T>>>> void setValue(Generic10<T> instance, T t) {
        instance.setValue(t);
    }

    public <T extends List<List<List<T>>>> void setValueNullCall(Generic10<T> instance, T t) {
        instance.setValue(null);
    }

    public <T extends List<List<List<T>>>> void fields(Generic10<T> instance) {
        T tValue = null;
        instance.t = tValue;
    }

}
