
package test.lib;

import lib.Nested;
import lib.Nested.Class1;
import lib.Nested.Class1.Class2;
import lib.Nested.Class1.Class2.Class3;
import lib.Nested.ClassA;
import lib.Nested.ClassA.ClassB;
import lib.Nested.ClassA.ClassB.ClassC;

public class NestedInstantiator {


    public Nested createNested() {
        return new Nested();
    }

    public class Class1Instantiator {


        public Class1 createClass1() {
            return new Class1();
        }

        public void m(Class1 instance) {
            instance.m();
        }

        public class Class2Instantiator {


            public Class2 createClass2() {
                return new Class2();
            }

            public void m(Class2 instance) {
                instance.m();
            }

            public class Class3Instantiator {


                public Class3 createClass3() {
                    return new Class3();
                }

                public void m(Class3 instance) {
                    instance.m();
                }

            }

        }

    }

    public class ClassAInstantiator {


        public ClassA createClassA(Nested instance) {
            return instance.new ClassA();
        }

        public void m(ClassA instance) {
            instance.m();
        }

        public class ClassBInstantiator {


            public ClassB createClassB(ClassA instance) {
                return instance.new ClassB();
            }

            public void m(ClassB instance) {
                instance.m();
            }

            public class ClassCInstantiator {


                public ClassC createClassC(ClassB instance) {
                    return instance.new ClassC();
                }

                public void m(ClassC instance) {
                    instance.m();
                }

            }

        }

    }

}
