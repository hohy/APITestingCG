
package test.lib;

import lib.Generic3;

public class Generic3Instantiator {


    public <T extends Number> Generic3<T> createGeneric3(T t) {
        return new Generic3<> (t);
    }

    public <T extends Number> T getValue(Generic3<T> instance) {
        return instance.getValue();
    }

    public <T extends Number> void setValue(Generic3<T> instance, T t) {
        instance.setValue(t);
    }

    public <T extends Number> void setValueNullCall(Generic3<T> instance, T t) {
        instance.setValue(null);
    }

    public <T extends Number> void fields(Generic3<T> instance) {
        T tValue = null;
        instance.t = tValue;
    }

}
