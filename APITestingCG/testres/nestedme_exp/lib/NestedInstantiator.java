
package test.lib;

import java.util.Map.Entry;
import java.util.Set;
import lib.Nested;

public class NestedInstantiator {


    public <K, V> Nested<K, V> createNested() {
        return new Nested<K, V>();
    }

    public <K, V> Set<Entry<K, V>> method(Nested<K, V> instance) {
        return instance.method();
    }

}
