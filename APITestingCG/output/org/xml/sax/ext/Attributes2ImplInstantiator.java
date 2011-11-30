package org.xml.sax.ext;

import org.xml.sax.ext.Attributes2Impl;

public class Attributes2ImplInstantiator {

	/*   CONSTRUCTORS   */

	public Attributes2ImplInstantiator () {
		org.xml.sax.ext.Attributes2Impl instance = new org.xml.sax.ext.Attributes2Impl();
	}

	public Attributes2ImplInstantiator (org.xml.sax.ext.Attributes2Impl a) {
		org.xml.sax.ext.Attributes2Impl instance = new org.xml.sax.ext.Attributes2Impl(a);
		org.xml.sax.ext.Attributes2Impl nullinstance = new org.xml.sax.ext.Attributes2Impl(null);
	}

	/*   METHODS   */

	public void addAttributeCall(java.lang.String a,java.lang.String b,java.lang.String c,java.lang.String d,java.lang.String e,org.xml.sax.ext.Attributes2Impl instance) {
		instance.addAttribute(a,b,c,d,e);
	}

	public void isDeclaredCall(int a,org.xml.sax.ext.Attributes2Impl instance) {
		boolean result = instance.isDeclared(a);
	}

	public void isDeclaredCall(java.lang.String a,java.lang.String b,org.xml.sax.ext.Attributes2Impl instance) {
		boolean result = instance.isDeclared(a,b);
	}

	public void isDeclaredCall(java.lang.String a,org.xml.sax.ext.Attributes2Impl instance) {
		boolean result = instance.isDeclared(a);
	}

	public void isSpecifiedCall(int a,org.xml.sax.ext.Attributes2Impl instance) {
		boolean result = instance.isSpecified(a);
	}

	public void isSpecifiedCall(java.lang.String a,java.lang.String b,org.xml.sax.ext.Attributes2Impl instance) {
		boolean result = instance.isSpecified(a,b);
	}

	public void isSpecifiedCall(java.lang.String a,org.xml.sax.ext.Attributes2Impl instance) {
		boolean result = instance.isSpecified(a);
	}

	public void removeAttributeCall(int a,org.xml.sax.ext.Attributes2Impl instance) {
		instance.removeAttribute(a);
	}

	public void setAttributesCall(org.xml.sax.Attributes a,org.xml.sax.ext.Attributes2Impl instance) {
		instance.setAttributes(a);
	}

	public void setDeclaredCall(int a,boolean b,org.xml.sax.ext.Attributes2Impl instance) {
		instance.setDeclared(a,b);
	}

	public void setSpecifiedCall(int a,boolean b,org.xml.sax.ext.Attributes2Impl instance) {
		instance.setSpecified(a,b);
	}

}
