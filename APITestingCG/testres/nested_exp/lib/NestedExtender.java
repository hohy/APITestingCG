
package test.lib;

import lib.Nested;
import lib.Nested.Class1;
import lib.Nested.Class1.Class2;
import lib.Nested.Class1.Class2.Class3;
import lib.Nested.ClassA;
import lib.Nested.ClassA.ClassB;
import lib.Nested.ClassA.ClassB.ClassC;

public class NestedExtender extends Nested {


    public NestedExtender() {
        super();
    }

    static class Class1Extender extends Class1 {


        public Class1Extender() {
            super();
        }

        @Override
        public void m() {
            throw new UnsupportedOperationException();
        }

        static class Class2Extender extends Class2 {


            public Class2Extender() {
                super();
            }

            @Override
            public void m() {
                throw new UnsupportedOperationException();
            }

            static class Class3Extender extends Class3 {


                public Class3Extender() {
                    super();
                }

                @Override
                public void m() {
                    throw new UnsupportedOperationException();
                }

            }

        }

    }

    class ClassAExtender extends ClassA {


        public ClassAExtender() {
            super();
        }

        @Override
        public void m() {
            throw new UnsupportedOperationException();
        }

        class ClassBExtender extends ClassB {


            public ClassBExtender() {
                super();
            }

            @Override
            public void m() {
                throw new UnsupportedOperationException();
            }

            class ClassCExtender extends ClassC {


                public ClassCExtender() {
                    super();
                }

                @Override
                public void m() {
                    throw new UnsupportedOperationException();
                }

            }

        }

    }

}
