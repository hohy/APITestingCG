
package test.lib;

import java.util.List;
import java.util.Set;
import lib.Generic;

public class GenericExtender<T extends List & Set, S> extends Generic<T, S> {


    public <U> GenericExtender(U paramA, T paramB) {
        super(paramA, paramB);
    }

    @Override
    public <V> V method(List<? extends List<S>> paramA, List<? super S> paramB) {
        throw new UnsupportedOperationException();
    }

}
