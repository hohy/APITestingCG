package org.xml.sax.helpers;

import org.xml.sax.helpers.AttributeListImpl;

public class AttributeListImplInstantiator {

	/*   CONSTRUCTORS   */

	public AttributeListImplInstantiator () {
		org.xml.sax.helpers.AttributeListImpl instance = new org.xml.sax.helpers.AttributeListImpl();
	}

	public AttributeListImplInstantiator (org.xml.sax.helpers.AttributeListImpl a) {
		org.xml.sax.helpers.AttributeListImpl instance = new org.xml.sax.helpers.AttributeListImpl(a);
		org.xml.sax.helpers.AttributeListImpl nullinstance = new org.xml.sax.helpers.AttributeListImpl(null);
	}

	/*   METHODS   */

	public void addAttributeCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.helpers.AttributeListImpl instance) {
		instance.addAttribute(a,b,c);
	}

	public void clearCall(org.xml.sax.helpers.AttributeListImpl instance) {
		instance.clear();
	}

	public void getLengthCall(org.xml.sax.helpers.AttributeListImpl instance) {
		int result = instance.getLength();
	}

	public void getNameCall(int a,org.xml.sax.helpers.AttributeListImpl instance) {
		String result = instance.getName(a);
	}

	public void getTypeCall(int a,org.xml.sax.helpers.AttributeListImpl instance) {
		String result = instance.getType(a);
	}

	public void getTypeCall(java.lang.String a,org.xml.sax.helpers.AttributeListImpl instance) {
		String result = instance.getType(a);
	}

	public void getValueCall(int a,org.xml.sax.helpers.AttributeListImpl instance) {
		String result = instance.getValue(a);
	}

	public void getValueCall(java.lang.String a,org.xml.sax.helpers.AttributeListImpl instance) {
		String result = instance.getValue(a);
	}

	public void removeAttributeCall(java.lang.String a,org.xml.sax.helpers.AttributeListImpl instance) {
		instance.removeAttribute(a);
	}

	public void setAttributeListCall(org.xml.sax.AttributeList a,org.xml.sax.helpers.AttributeListImpl instance) {
		instance.setAttributeList(a);
	}

}
