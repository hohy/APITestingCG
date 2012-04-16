
package test.lib;

import java.io.File;
import java.io.IOException;
import lib.ClassB;

public class ClassBExtender extends ClassB {


    public ClassBExtender() {
        super();
    }

    @Override
    public File fileMthd(int a, String name, File f) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void intParam(int a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int intReturn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void simple() {
        throw new UnsupportedOperationException();
    }

}
