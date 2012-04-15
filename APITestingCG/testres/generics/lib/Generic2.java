package lib;

public class Generic2<T, S> {

    public T t;
    public S s;

    public Generic2(T t, S s) {
        this.t = t;
        this.s = s;
    }

    public T getValue1() {
        return t;
    }

    public S getValue2() {
        return s;
    }

    public void setValue1(T t) {
    }

    public void setValue2(S s) {
    }

    public void setValues(T t, S s) {
    }
}