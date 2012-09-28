
package test.lib;

import java.util.List;
import java.util.Map;
import java.util.Set;
import lib.Generic17;

public class Generic17Instantiator {


    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> Generic17<T, S, V> createGeneric17(T t, S s, V v) {
        return new Generic17<T, S, V>(t, s, v);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> T getValue1(Generic17<T, S, V> instance) {
        return instance.getValue1();
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> S getValue2(Generic17<T, S, V> instance) {
        return instance.getValue2();
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> V getValue3(Generic17<T, S, V> instance) {
        return instance.getValue3();
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValue1(Generic17<T, S, V> instance, T t) {
        instance.setValue1(t);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValue1NullCall(Generic17<T, S, V> instance, T t) {
        instance.setValue1(null);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValue2(Generic17<T, S, V> instance, S s) {
        instance.setValue2(s);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValue2NullCall(Generic17<T, S, V> instance, S s) {
        instance.setValue2(null);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValue3(Generic17<T, S, V> instance, V v) {
        instance.setValue3(v);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValue3NullCall(Generic17<T, S, V> instance, V v) {
        instance.setValue3(null);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValues(Generic17<T, S, V> instance, T t, S s, V v) {
        instance.setValues(t, s, v);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void setValuesNullCall(Generic17<T, S, V> instance, T t, S s, V v) {
        instance.setValues(null, null, null);
    }

    public <T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> void fields(Generic17<T, S, V> instance) {
        S sValue = null;
        instance.s = sValue;
        T tValue = null;
        instance.t = tValue;
        V vValue = null;
        instance.v = vValue;
    }

}
