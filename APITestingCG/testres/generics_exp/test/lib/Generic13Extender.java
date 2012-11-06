
package test.lib;

import java.util.List;
import lib.Generic13;

public class Generic13Extender extends Generic13 {


    public Generic13Extender() {
        super();
    }

    @Override
    public void lowerBound(List<? super List<Integer>> lowerBound) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void noBound(List<?> noBound) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void upperBound(List<? extends Integer> upperBound) {
        throw new UnsupportedOperationException();
    }

}
