
package test.lib;

import lib.AbstractClass;

public abstract class AbstractClassExtender extends AbstractClass {


    public AbstractClassExtender() {
        super();
    }

    @Override
    public int abstractMethod() {
        throw new UnsupportedOperationException();
    }

}
