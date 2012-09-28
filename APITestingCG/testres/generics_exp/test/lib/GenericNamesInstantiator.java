
package test.lib;

import lib.GenericNames;
import lib.GenericNames.ClassNoNumber;
import lib.GenericNames.ClassNumber1;

public class GenericNamesInstantiator {


    public GenericNames createGenericNames() {
        return new GenericNames();
    }

    public void fields(GenericNames instance) {
        ClassNoNumber<String> noNumValue = null;
        instance.noNum = noNumValue;
        ClassNumber1<String> withNumValue = null;
        instance.withNum = withNumValue;
    }

    public class ClassNoNumberInstantiator {


        public <T> ClassNoNumber<T> createClassNoNumber(GenericNames instance) {
            return instance.new ClassNoNumber<> ();
        }

    }

    public class ClassNumber1Instantiator {


        public <T> ClassNumber1<T> createClassNumber1(GenericNames instance) {
            return instance.new ClassNumber1<> ();
        }

    }

}
