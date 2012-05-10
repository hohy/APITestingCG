
package test.lib;

import java.util.List;
import lib.Fields4;
import lib.Fields4.PublicNestedClass;

public class Fields4Extender extends Fields4 {


    public Fields4Extender() {
        super();
    }

    public void fields() {
        PublicNestedClass nested1Value = null;
        nested1 = nested1Value;
        PublicNestedClass<String> nested2Value = null;
        nested2 = nested2Value;
        ProtectedNestedClass nested3Value = null;
        nested3 = nested3Value;
        ProtectedNestedClass<String> nested4Value = null;
        nested4 = nested4Value;
        List<PublicNestedClass> nested7Value = null;
        nested7 = nested7Value;
        List<ProtectedNestedClass> nested8Value = null;
        nested8 = nested8Value;
    }

    class ProtectedNestedClassExtender<H> extends lib.Fields4.ProtectedNestedClass<H> {


        public ProtectedNestedClassExtender() {
            super();
        }

    }

    class PublicNestedClassExtender<G> extends PublicNestedClass<G> {


        public PublicNestedClassExtender() {
            super();
        }

    }

}
