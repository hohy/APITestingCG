package lib;

import java.util.Collection;

public class GenericConstructor1 {
 
    public <T> GenericConstructor1() {
    }
 
    public <T> GenericConstructor1(T t) {
    }
 
    public <T> GenericConstructor1(Collection<T> tt) {
    }
}