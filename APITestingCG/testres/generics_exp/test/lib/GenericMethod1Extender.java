
package test.lib;

import lib.GenericMethod1;

public class GenericMethod1Extender extends GenericMethod1 {


    public GenericMethod1Extender() {
        super();
    }

    @Override
    public <T> T getValue1(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Number> T getValue2(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Number & Runnable> T getValue3(T t) {
        throw new UnsupportedOperationException();
    }

}
