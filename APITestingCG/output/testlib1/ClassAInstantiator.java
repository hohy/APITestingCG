package testlib1;

import testlib1.ClassA;
import java.io.File;
public class ClassAInstantiator {

	// CONSTRUCTORS

	public ClassA createClassAInstance(Integer a) {
		return new ClassA(a);

	}

	public ClassA createClassAInstance(int a) {
		return new ClassA(a);

	}

	public ClassA createClassANullInstance() {
		return new ClassA(0);

	}

	public ClassA createClassAInstance(File a) {
		return new ClassA(a);

	}

	public ClassA createClassAInstance() {
		return new ClassA();

	}

	public ITestInterface createITestInterfaceInterfaceInstance(Integer a) {
		return new ClassA(a);

	}

	// METHODS

	public void apiMethodAddCall(Integer a, Integer b, ClassA instance) {
		int result = instance.apiMethodAdd(a,b);
	}

	public void apiMethodAddNullCall(Integer a, Integer b, ClassA instance) {
		int result = instance.apiMethodAdd(null,null);
	}

	public void doSomethingCall(ClassA instance) {
		instance.doSomething();
	}

	public void exceptionMethodCall(ClassA instance) {
		try {
			instance.exceptionMethod();
		} catch (java.lang.Exception ex) {}
	}

	public void staticMethodCall() {
		try {
			ClassA.staticMethod();
		} catch (java.io.IOException ex) {
		} catch (java.util.concurrent.TimeoutException ex) {}
	}

	public void CONSTField() {
		System.out.println(ClassA.CONST);
	}

	public void FILEField() {
		System.out.println(ClassA.FILE);
	}

	public void WORDField() {
		System.out.println(ClassA.WORD);
	}

	public void var1Field(ClassA instance) {
		instance.var1 = 0;
	}

	public void var2Field() {
		ClassA.var2 = null;
	}

}
