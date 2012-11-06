package lib;

public class Generic01<T> {

    public T t;

    public Generic01(T t) {
        this.t = t;
    }

    public T getValue() {
        return t;
    }

    public void setValue(T t) {
    }
}