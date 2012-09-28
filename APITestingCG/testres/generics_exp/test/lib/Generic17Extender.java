
package test.lib;

import java.util.List;
import java.util.Map;
import java.util.Set;
import lib.Generic17;

public class Generic17Extender<T extends List<T>, S extends List<Set<T>>, V extends Map<T, S>> extends Generic17<T, S, V> {


    public Generic17Extender(T t, S s, V v) {
        super(t, s, v);
    }

    @Override
    public T getValue1() {
        throw new UnsupportedOperationException();
    }

    @Override
    public S getValue2() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V getValue3() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue1(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue2(S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue3(V v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValues(T t, S s, V v) {
        throw new UnsupportedOperationException();
    }

    public void fields() {
        S sValue = null;
        s = sValue;
        T tValue = null;
        t = tValue;
        V vValue = null;
        v = vValue;
    }

}
