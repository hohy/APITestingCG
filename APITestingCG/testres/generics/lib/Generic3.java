package lib;

public class Generic3<T extends Number> {

    public T t;

    public Generic3(T t) {
        this.t = t;
    }

    public T getValue() {
        return t;
    }

    public void setValue(T t) {
    }
}