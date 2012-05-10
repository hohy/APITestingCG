
package test.lib;

import lib.Generic2;

public class Generic2Instantiator {


    public <T, S> Generic2 <T, S> createGeneric2(T t, S s) {
        return new Generic2 <T, S>(t, s);
    }

    public <T, S> T getValue1(Generic2<T, S> instance) {
        return instance.getValue1();
    }

    public <T, S> S getValue2(Generic2<T, S> instance) {
        return instance.getValue2();
    }

    public <T, S> void setValue1(Generic2<T, S> instance, T t) {
        instance.setValue1(t);
    }

    public <T, S> void setValue1NullCall(Generic2<T, S> instance, T t) {
        instance.setValue1(null);
    }

    public <T, S> void setValue2(Generic2<T, S> instance, S s) {
        instance.setValue2(s);
    }

    public <T, S> void setValue2NullCall(Generic2<T, S> instance, S s) {
        instance.setValue2(null);
    }

    public <T, S> void setValues(Generic2<T, S> instance, T t, S s) {
        instance.setValues(t, s);
    }

    public <T, S> void setValuesNullCall(Generic2<T, S> instance, T t, S s) {
        instance.setValues(null, null);
    }

    public <T, S> void fields(Generic2<T, S> instance) {
        S sValue = null;
        instance.s = sValue;
        T tValue = null;
        instance.t = tValue;
    }

}
