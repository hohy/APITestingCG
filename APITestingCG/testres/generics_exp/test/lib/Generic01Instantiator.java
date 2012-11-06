
package test.lib;

import lib.Generic01;

public class Generic01Instantiator {


    public <T> Generic01<T> createGeneric01(T t) {
        return new Generic01<T>(t);
    }

    public <T> T getValue(Generic01<T> instance) {
        return instance.getValue();
    }

    public <T> void setValue(Generic01<T> instance, T t) {
        instance.setValue(t);
    }

    public <T> void setValueNullCall(Generic01<T> instance, T t) {
        instance.setValue(null);
    }

    public <T> void fields(Generic01<T> instance) {
        T tValue = null;
        instance.t = tValue;
    }

}
