package test.lib;

import lib.ClassA;
import lib.Fields;

import java.io.File;

public class FieldsExtender extends Fields {


    public FieldsExtender() {
        super();
    }

    public void fields() {
        byte bValue = ((byte) 0);
        b = bValue;
        boolean bbValue = false;
        bb = bbValue;
        char cValue = 'a';
        c = cValue;
        double dValue = 0.0D;
        d = dValue;
        float fValue = 0.0F;
        f = fValue;
        int iValue = 0;
        i = iValue;
        long lValue = 0L;
        l = lValue;
        ClassA protectedFinalFld = super.protectedFinalFld;
        int protectedFinalPrimitiveFld = super.protectedFinalPrimitiveFld;
        ClassA protectedFldValue = null;
        protectedFld = protectedFldValue;
        int protectedPrimitiveFldValue = 0;
        protectedPrimitiveFld = protectedPrimitiveFldValue;
        ClassA protectedStaticFinalFld = Fields.protectedStaticFinalFld;
        int protectedStaticFinalPromitiveFld = Fields.protectedStaticFinalPromitiveFld;
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
        short sValue = ((short) 0);
        s = sValue;
        String strValue = null;
        str = strValue;
    }

}
