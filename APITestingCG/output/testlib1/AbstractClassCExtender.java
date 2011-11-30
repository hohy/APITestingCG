package testlib1;

import testlib1.AbstractClassC;
public class AbstractClassCExtender extends AbstractClassC {

	// CONSTRUCTORS

	public AbstractClassCExtender() {
		super();
	}

	// METHODS

	@Override
	public void abstractMethod() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void method() {
		throw new UnsupportedOperationException();
	}

}
