
package test.lib;

import lib.TestedClass;

public class TestedClassInstantiator {


    public TestedClass createTestedClass() {
        return new TestedClass();
    }

    public void fields(TestedClass instance) {
        boolean A = instance.A;
        String B = TestedClass.B;
        byte cValue = ((byte) 0);
        instance.c = cValue;
        long dValue = 0L;
        TestedClass.d = dValue;
    }

}
