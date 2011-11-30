package testlib1;

import testlib1.ClassB;
import java.io.File;
public class ClassBInstantiator {

	// CONSTRUCTORS

	public ClassB createClassBInstance(Double a, Double b) {
		return new ClassB(a,b);

	}

	public ClassB createClassBInstance(Integer a, Integer b) {
		return new ClassB(a,b);

	}

	public ClassB createClassBInstance(File a) {
		return new ClassB(a);

	}

	public ClassB createClassBNullInstance() {
		return new ClassB(null);

	}

	public ClassA createClassASuperInstance(Double a, Double b) {
		return new ClassB(a,b);

	}

	// METHODS

	public void someMethodCall(Double a, Double b, ClassB instance) {
		Object result = instance.someMethod(a,b);
	}

	public void someMethodCall(Integer a, Integer b, ClassB instance) {
		Object result = instance.someMethod(a,b);
	}

}
