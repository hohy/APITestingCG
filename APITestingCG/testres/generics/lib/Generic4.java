package lib;

public class Generic4<T extends Number & Runnable> {
 
    public T t;
 
    public Generic4(T t) {
        this.t = t;
    }
 
    public T getValue() {
        return t;
    }
 
    public void setValue(T t) {
    }
}
 