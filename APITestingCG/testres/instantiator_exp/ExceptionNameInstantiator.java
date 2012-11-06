
package test.lib;

import java.io.IOException;
import lib.ExceptionName;

public class ExceptionNameInstantiator {


    public ExceptionName createExceptionName() {
        return new ExceptionName();
    }

    public void error(ExceptionName instance, int e) {
        try {
            instance.error(e);
        } catch (IOException e1) {
        }
    }

    public void error(ExceptionName instance) {
        try {
            instance.error();
        } catch (IOException e) {
        }
    }

}
