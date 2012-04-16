
package test.lib;

import lib.Box;

public class BoxExtender<T> extends Box<T> {


    public BoxExtender() {
        super();
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get() {
        throw new UnsupportedOperationException();
    }

}
