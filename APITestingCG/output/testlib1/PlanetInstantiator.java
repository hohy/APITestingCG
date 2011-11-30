package testlib1;

import testlib1.Planet;
public class PlanetInstantiator {

	// CONSTRUCTORS

	// METHODS

	public void mainCall(String[] a) {
		Planet.main(a);
	}

	public void mainNullCall(String[] a) {
		Planet.main(null);
	}

	public void surfaceWeightCall(double a, Planet instance) {
		double result = instance.surfaceWeight(a);
	}

	public void surfaceWeightNullCall(double a, Planet instance) {
		double result = instance.surfaceWeight(0.0);
	}

	public void EARTHField() {
		System.out.println(Planet.EARTH);
	}

	public void GField() {
		System.out.println(Planet.G);
	}

	public void JUPITERField() {
		System.out.println(Planet.JUPITER);
	}

	public void MARSField() {
		System.out.println(Planet.MARS);
	}

	public void MERCURYField() {
		System.out.println(Planet.MERCURY);
	}

	public void NEPTUNEField() {
		System.out.println(Planet.NEPTUNE);
	}

	public void SATURNField() {
		System.out.println(Planet.SATURN);
	}

	public void URANUSField() {
		System.out.println(Planet.URANUS);
	}

	public void VENUSField() {
		System.out.println(Planet.VENUS);
	}

}
