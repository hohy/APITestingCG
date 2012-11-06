
package test.lib;

import java.util.List;
import lib.Generic13;

public class Generic13Instantiator {


    public Generic13 createGeneric13() {
        return new Generic13();
    }

    public void lowerBound(Generic13 instance, List<? super List<Integer>> lowerBound) {
        instance.lowerBound(lowerBound);
    }

    public void lowerBoundNullCall(Generic13 instance, List<? super List<Integer>> lowerBound) {
        instance.lowerBound(null);
    }

    public void noBound(Generic13 instance, List<?> noBound) {
        instance.noBound(noBound);
    }

    public void noBoundNullCall(Generic13 instance, List<?> noBound) {
        instance.noBound(null);
    }

    public void upperBound(Generic13 instance, List<? extends Integer> upperBound) {
        instance.upperBound(upperBound);
    }

    public void upperBoundNullCall(Generic13 instance, List<? extends Integer> upperBound) {
        instance.upperBound(null);
    }

}
