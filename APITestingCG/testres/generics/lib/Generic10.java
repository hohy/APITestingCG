package lib;

import java.util.List;

public class Generic10<T extends List<List<List<T>>>> {
 
    public T t;
 
    public Generic10(T t) {
        this.t = t;
    }
 
    public T getValue() {
        return t;
    }
 
    public void setValue(T t) {
    }
}