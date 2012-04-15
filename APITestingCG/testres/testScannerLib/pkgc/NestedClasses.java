package testScannerLib.pkgc;

public abstract class NestedClasses {

    public PublicInnerClass pub;
    public ProtectedInnerClass prot;
    public PublicStaticNestedClass pubStat;

    public PublicInnerClass publicMethod() {
        return null;
    }

    ;

    public ProtectedInnerClass protectedMethod() {
        return null;
    }

    public class PublicInnerClass {

        public PublicInnerClass(int par) {
        }

        public int publicInt;
        protected int protectedInt;

        public void publicMethod() {
        }

        ;

        protected void protectedMethod() {
        }

        ;
    }

    protected class ProtectedInnerClass {

        public ProtectedInnerClass(int par) {
        }

        public int publicInt;
        protected int protectedInt;

        public void publicMethod() {
        }

        ;

        protected void protectedMethod() {
        }

        ;
    }

    public static class PublicStaticNestedClass {

        public PublicStaticNestedClass(int par) {
        }

        public int publicInt;
        protected int protectedInt;

        public void publicMethod() {
        }

        ;

        protected void protectedMethod() {
        }

        ;
    }

    protected static class ProtectedStaticNestedClass {

        public ProtectedStaticNestedClass(int par) {
        }

        public int publicInt;
        protected int protectedInt;

        public void publicMethod() {
        }

        ;

        protected void protectedMethod() {
        }

        ;
    }
}