
package test.lib;

import java.util.List;
import lib.Fields4;
import lib.Fields4.PublicNestedClass;

public class Fields4Instantiator {


    public Fields4 createFields4() {
        return new Fields4();
    }

    public void fields(Fields4 instance) {
        PublicNestedClass nested1Value = null;
        instance.nested1 = nested1Value;
        PublicNestedClass<String> nested2Value = null;
        instance.nested2 = nested2Value;
        List<PublicNestedClass> nested7Value = null;
        instance.nested7 = nested7Value;
    }

    public class PublicNestedClassInstantiator {


        public <G> PublicNestedClass<G> createPublicNestedClass(Fields4 instance) {
            return instance.new PublicNestedClass<> ();
        }

    }

}
