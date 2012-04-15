package lib;

import java.io.File;
import java.util.List;
import java.util.Set;

public abstract class Constructors extends ClassB implements List, Set {

    public Constructors(int a) {
    }

    public Constructors(ClassA clsA) {
    }

    public Constructors(int a, ClassA clsA) {
    }

    public Constructors(int a, File f) {
    }

    public Constructors(ClassB a, Class b) {
    }

    public Constructors(ClassB s) {
    }

}