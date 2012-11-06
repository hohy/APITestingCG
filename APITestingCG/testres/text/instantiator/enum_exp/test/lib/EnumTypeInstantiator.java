
package test.lib;

import lib.EnumType;

public class EnumTypeInstantiator {


    public double method(EnumType instance) {
        return instance.method();
    }

    public void fields(EnumType instance) {
        EnumType A = EnumType.A;
        EnumType B = EnumType.B;
        EnumType C = EnumType.C;
        double FIELD = EnumType.FIELD;
    }

}
