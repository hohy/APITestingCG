package org.xml.sax;

import org.xml.sax.SAXException;

public class SAXExceptionInstantiator {

	/*   CONSTRUCTORS   */

	public SAXExceptionInstantiator () {
		org.xml.sax.SAXException instance = new org.xml.sax.SAXException();
	}

	public SAXExceptionInstantiator (org.xml.sax.SAXException a,org.xml.sax.SAXException b) {
		org.xml.sax.SAXException instance = new org.xml.sax.SAXException(a,b);
		org.xml.sax.SAXException nullinstance = new org.xml.sax.SAXException(null,null);
	}

	public SAXExceptionInstantiator (org.xml.sax.SAXException a) {
		org.xml.sax.SAXException instance = new org.xml.sax.SAXException(a);
		org.xml.sax.SAXException nullinstance = new org.xml.sax.SAXException(null);
	}

	/*   METHODS   */

	public void getCauseCall(org.xml.sax.SAXException instance) {
		Throwable result = instance.getCause();
	}

	public void getExceptionCall(org.xml.sax.SAXException instance) {
		Exception result = instance.getException();
	}

	public void getMessageCall(org.xml.sax.SAXException instance) {
		String result = instance.getMessage();
	}

	public void toStringCall(org.xml.sax.SAXException instance) {
		String result = instance.toString();
	}

}
