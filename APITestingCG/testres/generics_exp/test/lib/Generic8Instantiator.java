
package test.lib;

import java.util.List;
import lib.Generic8;

public class Generic8Instantiator {


    public <T extends List<T>> Generic8<T> createGeneric8() {
        return new Generic8<T>();
    }

}
