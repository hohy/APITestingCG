
package test.lib;

import java.util.Map.Entry;
import java.util.Set;
import lib.Nested;

public class NestedExtender<K, V> extends Nested<K, V> {


    public NestedExtender() {
        super();
    }

    @Override
    public Set<Entry<K, V>> method() {
        throw new UnsupportedOperationException();
    }

}
