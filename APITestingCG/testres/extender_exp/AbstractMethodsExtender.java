
package test.lib;

import java.util.List;
import lib.AbstractMethods;

public class AbstractMethodsExtender extends AbstractMethods {


    public AbstractMethodsExtender() {
        super();
    }

    @Override
    public <T> T execute2(List<? extends T> l) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int types1(int a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int[] types2(int[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String types3(String a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] types3(String[] a) {
        throw new UnsupportedOperationException();
    }

}
