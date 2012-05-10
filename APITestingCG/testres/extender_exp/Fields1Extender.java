
package test.lib;

import java.io.File;
import lib.ClassA;
import lib.Fields1;

public class Fields1Extender extends Fields1 {


    public Fields1Extender() {
        super();
    }

    public void fields() {
        ClassA protectedFinalFld = super.protectedFinalFld;
        int protectedFinalPrimitiveFld = super.protectedFinalPrimitiveFld;
        ClassA protectedFldValue = null;
        protectedFld = protectedFldValue;
        int protectedPrimitiveFldValue = 0;
        protectedPrimitiveFld = protectedPrimitiveFldValue;
        ClassA protectedStaticFinalFld = Fields1.protectedStaticFinalFld;
        int protectedStaticFinalPromitiveFld = Fields1.protectedStaticFinalPromitiveFld;
        ClassA protectedStaticFldValue = null;
        protectedStaticFld = protectedStaticFldValue;
        int protectedStaticPrimitiveFldValue = 0;
        protectedStaticPrimitiveFld = protectedStaticPrimitiveFldValue;
        File publicFldValue = null;
        publicFld = publicFldValue;
        int publicPrimitiveFldValue = 0;
        publicPrimitiveFld = publicPrimitiveFldValue;
        ClassA publicStaticFldValue = null;
        publicStaticFld = publicStaticFldValue;
        int publicStaticPrimitiveFldValue = 0;
        publicStaticPrimitiveFld = publicStaticPrimitiveFldValue;
    }

}
