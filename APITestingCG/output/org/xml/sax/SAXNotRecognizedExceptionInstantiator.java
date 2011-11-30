package org.xml.sax;

import org.xml.sax.SAXNotRecognizedException;

public class SAXNotRecognizedExceptionInstantiator {

	/*   CONSTRUCTORS   */

	public SAXNotRecognizedExceptionInstantiator () {
		org.xml.sax.SAXNotRecognizedException instance = new org.xml.sax.SAXNotRecognizedException();
	}

	public SAXNotRecognizedExceptionInstantiator (org.xml.sax.SAXNotRecognizedException a) {
		org.xml.sax.SAXNotRecognizedException instance = new org.xml.sax.SAXNotRecognizedException(a);
		org.xml.sax.SAXNotRecognizedException nullinstance = new org.xml.sax.SAXNotRecognizedException(null);
	}

	/*   METHODS   */

}
