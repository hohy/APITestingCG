package test.lib;

import lib.GenericMethod1;

public class GenericMethod1Instantiator {


    public <T extends Number> GenericMethod1 createGenericMethod1() {
        return new GenericMethod1();
    }

    public <T> T getValue1(GenericMethod1 instance, T t) {
        return instance.getValue1(t);
    }

    public <T> T getValue1NullCall(GenericMethod1 instance, T t) {
        return instance.getValue1(null);
    }

    public <T extends Number> T getValue2(GenericMethod1 instance, T t) {
        return instance.getValue2(t);
    }

    public <T extends Number> T getValue2NullCall(GenericMethod1 instance, T t) {
        return instance.getValue2(null);
    }

    public <T extends Number & Runnable> T getValue3(GenericMethod1 instance, T t) {
        return instance.getValue3(t);
    }

    public <T extends Number & Runnable> T getValue3NullCall(GenericMethod1 instance, T t) {
        return instance.getValue3(null);
    }

}
