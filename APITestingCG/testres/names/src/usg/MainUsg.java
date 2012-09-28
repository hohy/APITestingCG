package usg;
import lib.$Main;

public class MainUsg {

    public $Main.$NestedClass field;    
    
    public $Main.$NestedClass method($Main.$NestedClass param) {
        return null;
    };           
    
}

class MainExtender extends $Main {

    public $ProtectedNested field;
    public $Main.$ProtectedNested fieldAlt;

    @Override
    public $ProtectedNested protectedMethod($ProtectedNested param) {
        return null;
    };     
    
    public class NestedTest extends $ProtectedNested {

        @Override
        public void doNothing() {};
        
    }    
    
}
