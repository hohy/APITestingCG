package org.xml.sax.helpers;

import org.xml.sax.helpers.AttributesImpl;

public class AttributesImplInstantiator {

	/*   CONSTRUCTORS   */

	public AttributesImplInstantiator () {
		org.xml.sax.helpers.AttributesImpl instance = new org.xml.sax.helpers.AttributesImpl();
	}

	public AttributesImplInstantiator (org.xml.sax.helpers.AttributesImpl a) {
		org.xml.sax.helpers.AttributesImpl instance = new org.xml.sax.helpers.AttributesImpl(a);
		org.xml.sax.helpers.AttributesImpl nullinstance = new org.xml.sax.helpers.AttributesImpl(null);
	}

	/*   METHODS   */

	public void addAttributeCall(java.lang.String a,java.lang.String b,java.lang.String c,java.lang.String d,java.lang.String e,org.xml.sax.helpers.AttributesImpl instance) {
		instance.addAttribute(a,b,c,d,e);
	}

	public void clearCall(org.xml.sax.helpers.AttributesImpl instance) {
		instance.clear();
	}

	public void getIndexCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		int result = instance.getIndex(a,b);
	}

	public void getIndexCall(java.lang.String a,org.xml.sax.helpers.AttributesImpl instance) {
		int result = instance.getIndex(a);
	}

	public void getLengthCall(org.xml.sax.helpers.AttributesImpl instance) {
		int result = instance.getLength();
	}

	public void getLocalNameCall(int a,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getLocalName(a);
	}

	public void getQNameCall(int a,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getQName(a);
	}

	public void getTypeCall(int a,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getType(a);
	}

	public void getTypeCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getType(a,b);
	}

	public void getTypeCall(java.lang.String a,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getType(a);
	}

	public void getURICall(int a,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getURI(a);
	}

	public void getValueCall(int a,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getValue(a);
	}

	public void getValueCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getValue(a,b);
	}

	public void getValueCall(java.lang.String a,org.xml.sax.helpers.AttributesImpl instance) {
		String result = instance.getValue(a);
	}

	public void removeAttributeCall(int a,org.xml.sax.helpers.AttributesImpl instance) {
		instance.removeAttribute(a);
	}

	public void setAttributeCall(int a,java.lang.String b,java.lang.String c,java.lang.String d,java.lang.String e,java.lang.String f,org.xml.sax.helpers.AttributesImpl instance) {
		instance.setAttribute(a,b,c,d,e,f);
	}

	public void setAttributesCall(org.xml.sax.Attributes a,org.xml.sax.helpers.AttributesImpl instance) {
		instance.setAttributes(a);
	}

	public void setLocalNameCall(int a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		instance.setLocalName(a,b);
	}

	public void setQNameCall(int a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		instance.setQName(a,b);
	}

	public void setTypeCall(int a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		instance.setType(a,b);
	}

	public void setURICall(int a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		instance.setURI(a,b);
	}

	public void setValueCall(int a,java.lang.String b,org.xml.sax.helpers.AttributesImpl instance) {
		instance.setValue(a,b);
	}

}
