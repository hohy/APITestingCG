package lib;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Fields1<G> {
    
    // primitive type fields
    public byte pByte;
    public short pShort;
    public int pInt;
    public long pLong;
    public float pFloat;
    public double pDouble;
    public boolean pBoolean;
    public char pChar;

    // fields with various modifiers
    private int prvtFld = 1;
    public File publicFld = null;
    public static File publicStaticFld = null;
    public final File publicFinalFld = null;
    public static final File publicStaticFinalFld = null;
    public int publicPrimitiveFld = 2;
    public static int publicStaticPrimitiveFld = 2;
    protected final int protectedFinalPrimitiveFld = 2;
    protected static final int protectedStaticFinalPromitiveFld = 2;
    
    // fields with generics types
    public List<String> g1;
    public List<Map<Integer,List<List<Map<String,File>>>>> g2;
    public G g3;
    public List<G> g4;
    
    // nested type
    public NestedType n1;
    
    
    public class NestedType {
    
    }
}