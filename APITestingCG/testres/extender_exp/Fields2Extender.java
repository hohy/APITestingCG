
package test.lib;

import lib.Fields2;

public class Fields2Extender extends Fields2 {


    public Fields2Extender() {
        super();
    }

    public void fields() {
        boolean primitiveBooleanValue = false;
        primitiveBoolean = primitiveBooleanValue;
        byte primitiveByteValue = ((byte) 0);
        primitiveByte = primitiveByteValue;
        char primitiveCharValue = 'a';
        primitiveChar = primitiveCharValue;
        double primitiveDoubleValue = 0.0D;
        primitiveDouble = primitiveDoubleValue;
        float primitiveFloatValue = 0.0F;
        primitiveFloat = primitiveFloatValue;
        int primitiveIntegerValue = 0;
        primitiveInteger = primitiveIntegerValue;
        long primitiveLongValue = 0L;
        primitiveLong = primitiveLongValue;
        short primitiveShortValue = ((short) 0);
        primitiveShort = primitiveShortValue;
        String strValue = null;
        str = strValue;
    }

}
