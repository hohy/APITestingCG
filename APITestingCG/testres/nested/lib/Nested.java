package lib;

public class Nested {

    public static class Class1 {

        public void m() {
        }

        public static class Class2 {

            public void m() {
            }

            public static class Class3 {

                public void m() {
                }
            }
        }
    }

    public class ClassA {

        public void m() {
        }

        public class ClassB {

            public void m() {
            }

            public class ClassC {

                public void m() {
                }
            }
        }
    }
}