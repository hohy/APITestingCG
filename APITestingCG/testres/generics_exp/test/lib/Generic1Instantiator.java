
package test.lib;

import lib.Generic1;

public class Generic1Instantiator {


    public <T> Generic1<T> createGeneric1(T t) {
        return new Generic1<T>(t);
    }

    public <T> T getValue(Generic1<T> instance) {
        return instance.getValue();
    }

    public <T> void setValue(Generic1<T> instance, T t) {
        instance.setValue(t);
    }

    public <T> void setValueNullCall(Generic1<T> instance, T t) {
        instance.setValue(null);
    }

    public <T> void fields(Generic1<T> instance) {
        T tValue = null;
        instance.t = tValue;
    }

}
