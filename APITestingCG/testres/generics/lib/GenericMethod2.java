package lib;

import java.util.List;
import java.io.Serializable;

public class GenericMethod2 {
 
    public <T, S> T getValue1(T t, S s) {
        return t;
    }
 
    public <T extends Number & Runnable, S extends Serializable> T getValue2(T t, S s) {
        return t;
    }
 
    public <T extends Number & Runnable & Serializable, S extends List & Cloneable> T getValue3(T t, S s) {
        return t;
    }
}