
package test.lib;

import java.io.File;
import java.util.List;
import java.util.Map;
import lib.Fields3;

public class Fields3Instantiator {


    public <T> Fields3<T> createFields3() {
        return new Fields3<T>();
    }

    public <T> void fields(Fields3<T> instance) {
        List<Map<Integer, Map<String, List<File>>>> gListMapValue = null;
        instance.gListMap = gListMapValue;
        List<T> gListTValue = null;
        instance.gListT = gListTValue;
        T gTValue = null;
        instance.gT = gTValue;
        List<String> stringListValue = null;
        instance.stringList = stringListValue;
    }

}
