package testlib1;

import testlib1.ClassB;
import java.io.File;
public class ClassBExtender extends ClassB {

	// CONSTRUCTORS

	public ClassBExtender(Double a, Double b) {
		super(a,b);
	}

	public ClassBExtender(Integer a, Integer b) {
		super(a,b);
	}

	public ClassBExtender(File a) {
		super(a);
	}

	// METHODS

	@Override
	public Object someMethod(Double a, Double b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object someMethod(Integer a, Integer b) {
		throw new UnsupportedOperationException();
	}

}
