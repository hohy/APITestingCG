package org.xml.sax;

import org.xml.sax.SAXParseException;

public class SAXParseExceptionInstantiator {

	/*   CONSTRUCTORS   */

	public SAXParseExceptionInstantiator (org.xml.sax.SAXParseException a,org.xml.sax.SAXParseException b,org.xml.sax.SAXParseException c,org.xml.sax.SAXParseException d,org.xml.sax.SAXParseException e,org.xml.sax.SAXParseException f) {
		org.xml.sax.SAXParseException instance = new org.xml.sax.SAXParseException(a,b,c,d,e,f);
		org.xml.sax.SAXParseException nullinstance = new org.xml.sax.SAXParseException(null,null,null,null,null,null);
	}

	public SAXParseExceptionInstantiator (org.xml.sax.SAXParseException a,org.xml.sax.SAXParseException b,org.xml.sax.SAXParseException c,org.xml.sax.SAXParseException d,org.xml.sax.SAXParseException e) {
		org.xml.sax.SAXParseException instance = new org.xml.sax.SAXParseException(a,b,c,d,e);
		org.xml.sax.SAXParseException nullinstance = new org.xml.sax.SAXParseException(null,null,null,null,null);
	}

	public SAXParseExceptionInstantiator (org.xml.sax.SAXParseException a,org.xml.sax.SAXParseException b,org.xml.sax.SAXParseException c) {
		org.xml.sax.SAXParseException instance = new org.xml.sax.SAXParseException(a,b,c);
		org.xml.sax.SAXParseException nullinstance = new org.xml.sax.SAXParseException(null,null,null);
	}

	public SAXParseExceptionInstantiator (org.xml.sax.SAXParseException a,org.xml.sax.SAXParseException b) {
		org.xml.sax.SAXParseException instance = new org.xml.sax.SAXParseException(a,b);
		org.xml.sax.SAXParseException nullinstance = new org.xml.sax.SAXParseException(null,null);
	}

	/*   METHODS   */

	public void getColumnNumberCall(org.xml.sax.SAXParseException instance) {
		int result = instance.getColumnNumber();
	}

	public void getLineNumberCall(org.xml.sax.SAXParseException instance) {
		int result = instance.getLineNumber();
	}

	public void getPublicIdCall(org.xml.sax.SAXParseException instance) {
		String result = instance.getPublicId();
	}

	public void getSystemIdCall(org.xml.sax.SAXParseException instance) {
		String result = instance.getSystemId();
	}

	public void toStringCall(org.xml.sax.SAXParseException instance) {
		String result = instance.toString();
	}

}
