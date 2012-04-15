package lib;

import java.io.File;

public class ClassB {

    public ClassB() {

    }

    public ClassB(int a, float c) {

    }

    @Deprecated
    public ClassB(int a, int b, File x) {
    }

    public void method() {
    }

    @Deprecated
    public void deprecatedMethod() {
    }

    /**
     * @deprecated Since 2.1.1
     */
    public void otherDeprecatedMethod() {
    }

    public int field = 1;

    @Deprecated
    public int deprecatedField = 2;

}