
package test.lib;

import java.io.Serializable;
import lib.TypeParams;

public class TypeParamsExtender extends TypeParams {


    public TypeParamsExtender() {
        super();
    }

    @Override
    public <T extends Number & Runnable, S extends Serializable> T getValue1(T t, S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Number & Runnable, T extends Serializable> T getValue2(T t, S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Serializable, T extends Number & Runnable> T getValue3(S t, T s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Serializable, T extends Number & Runnable> T getValue4(S t, T s) {
        throw new UnsupportedOperationException();
    }

}
