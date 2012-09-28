package lib;

import java.util.List;
import java.util.Set;

public class Generic12<T extends List<T> & Set<T>> {
 
    public T t;
 
    public Generic12(T t) {
        this.t = t;
    }
 
    public T getValue() {
        return t;
    }
 
    public void setValue(T t) {
    }
}