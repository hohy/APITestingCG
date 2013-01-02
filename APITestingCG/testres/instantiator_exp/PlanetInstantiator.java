
package test.lib;

import java.io.File;
import lib.Planet;

public class PlanetInstantiator {


    public void main(String[] args) {
        Planet.main(args);
    }

    public void mainNullCall(String[] args) {
        Planet.main(null);
    }

    public void method(String s) {
        Planet.method(s);
    }

    public void methodNullCall(String s) {
        Planet.method(null);
    }

    public double surfaceWeight(Planet instance, double otherMass) {
        return instance.surfaceWeight(otherMass);
    }

    public String valueOf(File f, int a) {
        return Planet.valueOf(f, a);
    }

    public String valueOfNullCall(File f, int a) {
        return Planet.valueOf(null, 0);
    }

    public String valueOf(File f) {
        return Planet.valueOf(f);
    }

    public Planet[] values(String name) {
        return Planet.values(name);
    }

    public Planet[] valuesNullCall(String name) {
        return Planet.values(null);
    }

    public void fields(Planet instance) {
        Planet EARTH = Planet.EARTH;
        double G = Planet.G;
        Planet JUPITER = Planet.JUPITER;
        Planet MARS = Planet.MARS;
        Planet MERCURY = Planet.MERCURY;
        Planet NEPTUNE = Planet.NEPTUNE;
        Planet SATURN = Planet.SATURN;
        Planet URANUS = Planet.URANUS;
        Planet VENUS = Planet.VENUS;
    }

}
