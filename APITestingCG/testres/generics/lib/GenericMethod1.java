package lib;

public class GenericMethod1 {

    public <T> T getValue1(T t) {
        return t;
    }

    public <T extends Number> T getValue2(T t) {
        return t;
    }

    public <T extends Number & Runnable> T getValue3(T t) {
        return t;
    }
}