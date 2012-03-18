package testAPIFieldRes;

import java.io.File;
import java.util.List;
import java.util.Map;

public class TestAPIField {
    public static final int INT_CONST = 12345;
    public final static Object OBJECT_CONST = null;
    protected float floatVar;
    double doubleVar;
    protected final double DOUBLE_CONST = 5.0;
    public File file;
    // generics test
    public Map<String, List<File>> xfileListMap;
}