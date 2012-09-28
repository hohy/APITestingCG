
package test.lib;

import java.io.File;
import lib.ClassA;
import lib.Fields1;

public class Fields1Instantiator {


    public Fields1 createFields1() {
        return new Fields1();
    }

    public void fields(Fields1 instance) {
        File publicFldValue = null;
        instance.publicFld = publicFldValue;
        int publicPrimitiveFldValue = 0;
        instance.publicPrimitiveFld = publicPrimitiveFldValue;
        ClassA publicStaticFldValue = null;
        Fields1.publicStaticFld = publicStaticFldValue;
        int publicStaticPrimitiveFldValue = 0;
        Fields1.publicStaticPrimitiveFld = publicStaticPrimitiveFldValue;
    }

}
