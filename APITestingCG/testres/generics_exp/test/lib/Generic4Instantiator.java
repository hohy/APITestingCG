
package test.lib;

import lib.Generic4;

public class Generic4Instantiator {


    public <T extends Number & Runnable> Generic4<T> createGeneric4(T t) {
        return new Generic4<> (t);
    }

    public <T extends Number & Runnable> T getValue(Generic4<T> instance) {
        return instance.getValue();
    }

    public <T extends Number & Runnable> void setValue(Generic4<T> instance, T t) {
        instance.setValue(t);
    }

    public <T extends Number & Runnable> void setValueNullCall(Generic4<T> instance, T t) {
        instance.setValue(null);
    }

    public <T extends Number & Runnable> void fields(Generic4<T> instance) {
        T tValue = null;
        instance.t = tValue;
    }

}
