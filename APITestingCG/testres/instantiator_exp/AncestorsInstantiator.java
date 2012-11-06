
package test.lib;

import lib.Ancestors;
import lib.ClassA;

public class AncestorsInstantiator {


     void ancestors(Ancestors a) {
        ClassA b = a;
        Runnable c = a;
    }

    public void run(Ancestors instance) {
        instance.run();
    }

}
