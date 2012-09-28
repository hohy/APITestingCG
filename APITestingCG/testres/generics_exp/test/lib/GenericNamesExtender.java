
package test.lib;

import lib.GenericNames;
import lib.GenericNames.ClassNoNumber;
import lib.GenericNames.ClassNumber1;

public class GenericNamesExtender extends GenericNames {


    public GenericNamesExtender() {
        super();
    }

    public void fields() {
        ClassNoNumber<String> noNumValue = null;
        noNum = noNumValue;
        ClassNumber1<String> withNumValue = null;
        withNum = withNumValue;
    }

    class ClassNoNumberExtender<T> extends ClassNoNumber<T> {


        public ClassNoNumberExtender() {
            super();
        }

    }

    class ClassNumber1Extender<T> extends ClassNumber1<T> {


        public ClassNumber1Extender() {
            super();
        }

    }

}
