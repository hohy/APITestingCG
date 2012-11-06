package lib;

import java.util.List;
import java.util.Set;

public class Generic<T extends List & Set, S> {
    
    public <U> Generic(U paramA, T paramB) {}
    
    public <V> V method(List<? extends List<S>> paramA, List<? super S> paramB) {
        return null;
    }
    
}
