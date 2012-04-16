
package nodeprecated.lib;

import lib.ClassB;

public class ClassBExtender extends ClassB {


    public ClassBExtender(int a, float c) {
        super(a, c);
    }

    public ClassBExtender() {
        super();
    }

    @Override
    public void method() {
        throw new UnsupportedOperationException();
    }

    public void fields() {
        int fieldValue = 0;
        field = fieldValue;
    }

}
