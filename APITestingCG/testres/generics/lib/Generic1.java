package lib;

public class Generic1<T> {

    public T t;

    public Generic1(T t) {
        this.t = t;
    }

    public T getValue() {
        return t;
    }

    public void setValue(T t) {
    }
}