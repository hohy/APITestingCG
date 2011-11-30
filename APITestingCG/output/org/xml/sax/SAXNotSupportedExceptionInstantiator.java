package org.xml.sax;

import org.xml.sax.SAXNotSupportedException;

public class SAXNotSupportedExceptionInstantiator {

	/*   CONSTRUCTORS   */

	public SAXNotSupportedExceptionInstantiator () {
		org.xml.sax.SAXNotSupportedException instance = new org.xml.sax.SAXNotSupportedException();
	}

	public SAXNotSupportedExceptionInstantiator (org.xml.sax.SAXNotSupportedException a) {
		org.xml.sax.SAXNotSupportedException instance = new org.xml.sax.SAXNotSupportedException(a);
		org.xml.sax.SAXNotSupportedException nullinstance = new org.xml.sax.SAXNotSupportedException(null);
	}

	/*   METHODS   */

}
