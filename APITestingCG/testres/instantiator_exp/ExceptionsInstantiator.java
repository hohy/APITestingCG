
package test.lib;

import lib.Exceptions;

public class ExceptionsInstantiator {


    public Exceptions createExceptions() {
        return new Exceptions();
    }

    public void m(Exceptions instance) {
        try {
            instance.m();
        } catch (Exception e) {
        }
    }

}
