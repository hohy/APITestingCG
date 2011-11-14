package testlib1;

public class ClassAInstantiator {

	public ClassAInstantiator (Integer a) {
		ClassA instance = new ClassA(a)
	}

	public ClassAInstantiator (int a) {
		ClassA instance = new ClassA(a)
	}

	public ClassAInstantiator (java.io.File a) {
		ClassA instance = new ClassA(a)
	}

	public ClassAInstantiator () {
		ClassA instance = new ClassA()
	}

}
