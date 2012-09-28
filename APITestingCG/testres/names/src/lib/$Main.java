package lib;

public class $Main {
    
    public $NestedClass field;
    
    public $NestedClass method($NestedClass param) {
        return null;        
    }
    
    protected $ProtectedNested protectedMethod($ProtectedNested param) {
        return null;
    }
    
    public class $NestedClass {
        public void doNothing() {};
        
        public class $NestedClassL2 {
            public void doNothing() {};
        } 
    }
    
    protected class $ProtectedNested {
        public void doNothing() {};
    }
        
}
