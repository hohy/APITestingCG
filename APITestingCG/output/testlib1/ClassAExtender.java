package testlib1;

import testlib1.ClassA;
import java.io.File;
public class ClassAExtender extends ClassA {

	// CONSTRUCTORS

	public ClassAExtender(Integer a) {
		super(a);
	}

	public ClassAExtender(int a) {
		super(a);
	}

	public ClassAExtender(File a) {
		super(a);
	}

	public ClassAExtender() {
		super();
	}

	// METHODS

	@Override
	public int apiMethodAdd(Integer a, Integer b) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected int apiMethodSub(int a, int b) throws java.io.IOException, java.util.concurrent.TimeoutException{
		throw new UnsupportedOperationException();
	}

	@Override
	public void doSomething() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void exceptionMethod() throws java.lang.Exception{
		throw new UnsupportedOperationException();
	}

}
