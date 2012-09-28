package lib;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Fields3<T> {

    public List<String> stringList;

    public T gT;
    public List<T> gListT;
    public List<Map<Integer,Map<String,List<File>>>> gListMap;

}