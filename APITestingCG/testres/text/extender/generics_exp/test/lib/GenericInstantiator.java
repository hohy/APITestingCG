
package test.lib;

import java.util.List;
import java.util.Set;
import lib.Generic;

public class GenericInstantiator {


    public <U, T extends List & Set, S> Generic<T, S> createGeneric(U paramA, T paramB) {
        return new Generic<T, S>(paramA, paramB);
    }

    public <V, T extends List & Set, S> V method(Generic<T, S> instance, List<? extends List<S>> paramA, List<? super S> paramB) {
        return instance.method(paramA, paramB);
    }

    public <V, T extends List & Set, S> V methodNullCall(Generic<T, S> instance, List<? extends List<S>> paramA, List<? super S> paramB) {
        return instance.method(null, null);
    }

}
