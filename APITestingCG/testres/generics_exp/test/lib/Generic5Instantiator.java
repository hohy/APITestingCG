
package test.lib;

import lib.Generic5;

public class Generic5Instantiator {


    public <T extends Number & Runnable> Generic5<T> createGeneric5() {
        return new Generic5<T>();
    }

}
