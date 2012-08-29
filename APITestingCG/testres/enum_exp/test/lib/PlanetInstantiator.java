
package test.lib;

import lib.Planet;

public class PlanetInstantiator {


    public void main(String[] args) {
        Planet.main(args);
    }

    public void mainNullCall(String[] args) {
        Planet.main(null);
    }

    public double surfaceWeight(Planet instance, double otherMass) {
        return instance.surfaceWeight(otherMass);
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
