
package test.lib;

import java.io.Serializable;
import lib.TypeParams;

public class TypeParamsInstantiator {


    public TypeParams createTypeParams() {
        return new TypeParams();
    }

    public <T extends Number & Runnable, S extends Serializable> T getValue1(TypeParams instance, T t, S s) {
        return instance.getValue1(t, s);
    }

    public <T extends Number & Runnable, S extends Serializable> T getValue1NullCall(TypeParams instance, T t, S s) {
        return instance.getValue1(null, null);
    }

    public <S extends Number & Runnable, T extends Serializable> T getValue2(TypeParams instance, T t, S s) {
        return instance.getValue2(t, s);
    }

    public <S extends Number & Runnable, T extends Serializable> T getValue2NullCall(TypeParams instance, T t, S s) {
        return instance.getValue2(null, null);
    }

    public <S extends Serializable, T extends Number & Runnable> T getValue3(TypeParams instance, S t, T s) {
        return instance.getValue3(t, s);
    }

    public <S extends Serializable, T extends Number & Runnable> T getValue3NullCall(TypeParams instance, S t, T s) {
        return instance.getValue3(null, null);
    }

    public <S extends Serializable, T extends Number & Runnable> T getValue4(TypeParams instance, S t, T s) {
        return instance.getValue4(t, s);
    }

    public <S extends Serializable, T extends Number & Runnable> T getValue4NullCall(TypeParams instance, S t, T s) {
        return instance.getValue4(null, null);
    }

}
