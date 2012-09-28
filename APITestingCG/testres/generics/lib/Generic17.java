package lib;

import java.util.List;
import java.util.Set;
import java.util.Map;

public class Generic17<T extends List<T>, S extends List<Set<T>>, V
extends Map<T, S>> {
 
    public T t;
    public S s;
    public V v;
 
    public Generic17(T t, S s, V v) {
        this.t = t;
        this.s = s;
        this.v = v;
    }
 
    public T getValue1() {
        return t;
    }
 
    public S getValue2() {
        return s;
    }
 
    public V getValue3() {
        return v;
    }
 
    public void setValue1(T t) {
    }
 
    public void setValue2(S s) {
    }
 
    public void setValue3(V v) {
    }
 
    public void setValues(T t, S s, V v) {
    }
}