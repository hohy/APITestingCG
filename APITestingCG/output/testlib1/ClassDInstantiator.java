package testlib1;

import testlib1.ClassD;
import java.util.List;
import java.util.Collection;
public class ClassDInstantiator {

	// CONSTRUCTORS

	public ClassD createClassDInstance() {
		return new ClassD();

	}

	public List createListInterfaceInstance() {
		return new ClassD();

	}

	// METHODS

	public void addAllCall(int a, Collection b, ClassD instance) {
		boolean result = instance.addAll(a,b);
	}

	public void addAllCall(Collection a, ClassD instance) {
		boolean result = instance.addAll(a);
	}

	public void addCall(Object a, ClassD instance) {
		boolean result = instance.add(a);
	}

	public void addCall(int a, Object b, ClassD instance) {
		instance.add(a,b);
	}

	public void clearCall(ClassD instance) {
		instance.clear();
	}

	public void containsAllCall(Collection a, ClassD instance) {
		boolean result = instance.containsAll(a);
	}

	public void containsCall(Object a, ClassD instance) {
		boolean result = instance.contains(a);
	}

	public void getCall(int a, ClassD instance) {
		Object result = instance.get(a);
	}

	public void indexOfCall(Object a, ClassD instance) {
		int result = instance.indexOf(a);
	}

	public void isEmptyCall(ClassD instance) {
		boolean result = instance.isEmpty();
	}

	public void iteratorCall(ClassD instance) {
		java.util.Iterator result = instance.iterator();
	}

	public void lastIndexOfCall(Object a, ClassD instance) {
		int result = instance.lastIndexOf(a);
	}

	public void listIteratorCall(int a, ClassD instance) {
		java.util.ListIterator result = instance.listIterator(a);
	}

	public void listIteratorCall(ClassD instance) {
		java.util.ListIterator result = instance.listIterator();
	}

	public void removeAllCall(Collection a, ClassD instance) {
		boolean result = instance.removeAll(a);
	}

	public void removeCall(Object a, ClassD instance) {
		boolean result = instance.remove(a);
	}

	public void removeCall(int a, ClassD instance) {
		Object result = instance.remove(a);
	}

	public void retainAllCall(Collection a, ClassD instance) {
		boolean result = instance.retainAll(a);
	}

	public void setCall(int a, Object b, ClassD instance) {
		Object result = instance.set(a,b);
	}

	public void sizeCall(ClassD instance) {
		int result = instance.size();
	}

	public void subListCall(int a, int b, ClassD instance) {
		java.util.List result = instance.subList(a,b);
	}

	public void subListNullCall(int a, int b, ClassD instance) {
		java.util.List result = instance.subList(0,0);
	}

	public void toArrayCall(ClassD instance) {
		Object[] result = instance.toArray();
	}

	public void toArrayCall(Object[] a, ClassD instance) {
		Object[] result = instance.toArray(a);
	}

}
