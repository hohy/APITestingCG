package testlib1;

public class ClassBInstantiator {

	public ClassBInstantiator (Double a,Double b) {
		ClassB instance = new ClassB(a,b)
	}

	public ClassBInstantiator (Integer a,Integer b) {
		ClassB instance = new ClassB(a,b)
	}

	public ClassBInstantiator (java.io.File a) {
		ClassB instance = new ClassB(a)
	}

	public ClassBInstantiator () {
		ClassB instance = new ClassB()
	}

}
