package org.xml.sax.helpers;

import org.xml.sax.helpers.LocatorImpl;

public class LocatorImplInstantiator {

	/*   CONSTRUCTORS   */

	public LocatorImplInstantiator () {
		org.xml.sax.helpers.LocatorImpl instance = new org.xml.sax.helpers.LocatorImpl();
	}

	public LocatorImplInstantiator (org.xml.sax.helpers.LocatorImpl a) {
		org.xml.sax.helpers.LocatorImpl instance = new org.xml.sax.helpers.LocatorImpl(a);
		org.xml.sax.helpers.LocatorImpl nullinstance = new org.xml.sax.helpers.LocatorImpl(null);
	}

	/*   METHODS   */

	public void getColumnNumberCall(org.xml.sax.helpers.LocatorImpl instance) {
		int result = instance.getColumnNumber();
	}

	public void getLineNumberCall(org.xml.sax.helpers.LocatorImpl instance) {
		int result = instance.getLineNumber();
	}

	public void getPublicIdCall(org.xml.sax.helpers.LocatorImpl instance) {
		String result = instance.getPublicId();
	}

	public void getSystemIdCall(org.xml.sax.helpers.LocatorImpl instance) {
		String result = instance.getSystemId();
	}

	public void setColumnNumberCall(int a,org.xml.sax.helpers.LocatorImpl instance) {
		instance.setColumnNumber(a);
	}

	public void setLineNumberCall(int a,org.xml.sax.helpers.LocatorImpl instance) {
		instance.setLineNumber(a);
	}

	public void setPublicIdCall(java.lang.String a,org.xml.sax.helpers.LocatorImpl instance) {
		instance.setPublicId(a);
	}

	public void setSystemIdCall(java.lang.String a,org.xml.sax.helpers.LocatorImpl instance) {
		instance.setSystemId(a);
	}

}
