
package test.lib;

import java.io.File;
import java.util.List;
import java.util.Map;
import lib.Fields3;

public class Fields3Extender<T> extends Fields3<T> {


    public Fields3Extender() {
        super();
    }

    public void fields() {
        List<Map<Integer, Map<String, List<File>>>> gListMapValue = null;
        gListMap = gListMapValue;
        List<T> gListTValue = null;
        gListT = gListTValue;
        T gTValue = null;
        gT = gTValue;
        List<String> stringListValue = null;
        stringList = stringListValue;
    }

}
