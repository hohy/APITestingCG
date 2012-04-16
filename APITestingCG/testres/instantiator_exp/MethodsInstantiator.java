
package test.lib;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import lib.Methods;

public class MethodsInstantiator {


    public Methods createMethods() {
        return new Methods();
    }

    public void exceptions(Methods instance, int a, File b) {
        try {
            instance.exceptions(a, b);
        } catch (RuntimeException e) {
        } catch (IOException e) {
        }
    }

    public void exceptionsNullCall(Methods instance, int a, File b) {
        try {
            instance.exceptions(0, null);
        } catch (RuntimeException e) {
        } catch (IOException e) {
        }
    }

    public int intReturn(Methods instance) {
        return instance.intReturn();
    }

    public boolean methodBoolean(Methods instance, boolean b) {
        return instance.methodBoolean(b);
    }

    public byte methodByte(Methods instance, byte b) {
        return instance.methodByte(b);
    }

    public char methodChar(Methods instance, char ch) {
        return instance.methodChar(ch);
    }

    public double methodDouble(Methods instance, double d) {
        return instance.methodDouble(d);
    }

    public float methodFloat(Methods instance, float f) {
        return instance.methodFloat(f);
    }

    public int methodInt(Methods instance, int i) {
        return instance.methodInt(i);
    }

    public long methodLong(Methods instance, long l) {
        return instance.methodLong(l);
    }

    public short methodShort(Methods instance, short s) {
        return instance.methodShort(s);
    }

    public void simple(Methods instance) {
        instance.simple();
    }

    public File types2(List<Set> x) {
        return Methods.types2(x);
    }

    public File types2NullCall(List<Set> x) {
        return Methods.types2(null);
    }

    public List types(Methods instance, int a, File b, Set c) {
        return instance.types(a, b, c);
    }

    public List types(Methods instance, int a, List b, String c) {
        return instance.types(a, b, c);
    }

}
