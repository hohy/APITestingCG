package test.lib;

import lib.ClassA;

import java.io.File;
import java.util.List;

public class ClassAInstantiator {


    public ClassA createClassA(int a, File f, List<String> l) {
        return new ClassA(a, f, l);
    }

    public ClassA createClassA(int a) {
        return new ClassA(a);
    }

    public ClassA createClassA() {
        return new ClassA();
    }

}
