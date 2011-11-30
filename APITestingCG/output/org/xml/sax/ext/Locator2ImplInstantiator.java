package org.xml.sax.ext;

import org.xml.sax.ext.Locator2Impl;

public class Locator2ImplInstantiator {

	/*   CONSTRUCTORS   */

	public Locator2ImplInstantiator () {
		org.xml.sax.ext.Locator2Impl instance = new org.xml.sax.ext.Locator2Impl();
	}

	public Locator2ImplInstantiator (org.xml.sax.ext.Locator2Impl a) {
		org.xml.sax.ext.Locator2Impl instance = new org.xml.sax.ext.Locator2Impl(a);
		org.xml.sax.ext.Locator2Impl nullinstance = new org.xml.sax.ext.Locator2Impl(null);
	}

	/*   METHODS   */

	public void getEncodingCall(org.xml.sax.ext.Locator2Impl instance) {
		String result = instance.getEncoding();
	}

	public void getXMLVersionCall(org.xml.sax.ext.Locator2Impl instance) {
		String result = instance.getXMLVersion();
	}

	public void setEncodingCall(java.lang.String a,org.xml.sax.ext.Locator2Impl instance) {
		instance.setEncoding(a);
	}

	public void setXMLVersionCall(java.lang.String a,org.xml.sax.ext.Locator2Impl instance) {
		instance.setXMLVersion(a);
	}

}
