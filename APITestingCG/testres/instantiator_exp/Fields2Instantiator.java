
package test.lib;

import lib.Fields2;

public class Fields2Instantiator {


    public Fields2 createFields2() {
        return new Fields2();
    }

    public void fields(Fields2 instance) {
        boolean primitiveBooleanValue = false;
        instance.primitiveBoolean = primitiveBooleanValue;
        byte primitiveByteValue = ((byte) 0);
        instance.primitiveByte = primitiveByteValue;
        char primitiveCharValue = 'a';
        instance.primitiveChar = primitiveCharValue;
        double primitiveDoubleValue = 0.0D;
        instance.primitiveDouble = primitiveDoubleValue;
        float primitiveFloatValue = 0.0F;
        instance.primitiveFloat = primitiveFloatValue;
        int primitiveIntegerValue = 0;
        instance.primitiveInteger = primitiveIntegerValue;
        long primitiveLongValue = 0L;
        instance.primitiveLong = primitiveLongValue;
        short primitiveShortValue = ((short) 0);
        instance.primitiveShort = primitiveShortValue;
        String strValue = null;
        instance.str = strValue;
    }

}
