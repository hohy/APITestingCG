package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Methods {

    public void simple() {
    }

    public int intReturn() {
        return 1;
    }

    public List types(int a, File b, Set c) {
        return null;
    }

    public List types(int a, List b, String c) {
        return null;
    }

    public static File types2(List<Set> x) {
        return null;
    }

    public void exceptions(int a, File b)
            throws RuntimeException, FileNotFoundException, IOException {
    }

    public byte methodByte(byte b) {
        return b;
    }

    public short methodShort(short s) {
        return s;
    }

    public int methodInt(int i) {
        return i;
    }

    public long methodLong(long l) {
        return l;
    }

    public float methodFloat(float f) {
        return f;
    }

    public double methodDouble(double d) {
        return d;
    }

    public boolean methodBoolean(boolean b) {
        return b;
    }

    public char methodChar(char ch) {
        return ch;
    }
}