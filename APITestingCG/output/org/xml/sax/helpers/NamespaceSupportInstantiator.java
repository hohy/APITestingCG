package org.xml.sax.helpers;

import org.xml.sax.helpers.NamespaceSupport;

public class NamespaceSupportInstantiator {

	/*   CONSTRUCTORS   */

	public NamespaceSupportInstantiator () {
		org.xml.sax.helpers.NamespaceSupport instance = new org.xml.sax.helpers.NamespaceSupport();
	}

	/*   METHODS   */

	public void declarePrefixCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.NamespaceSupport instance) {
		boolean result = instance.declarePrefix(a,b);
	}

	public void getDeclaredPrefixesCall(org.xml.sax.helpers.NamespaceSupport instance) {
		Enumeration result = instance.getDeclaredPrefixes();
	}

	public void getPrefixesCall(org.xml.sax.helpers.NamespaceSupport instance) {
		Enumeration result = instance.getPrefixes();
	}

	public void getPrefixesCall(java.lang.String a,org.xml.sax.helpers.NamespaceSupport instance) {
		Enumeration result = instance.getPrefixes(a);
	}

	public void getPrefixCall(java.lang.String a,org.xml.sax.helpers.NamespaceSupport instance) {
		String result = instance.getPrefix(a);
	}

	public void getURICall(java.lang.String a,org.xml.sax.helpers.NamespaceSupport instance) {
		String result = instance.getURI(a);
	}

	public void isNamespaceDeclUrisCall(org.xml.sax.helpers.NamespaceSupport instance) {
		boolean result = instance.isNamespaceDeclUris();
	}

	public void popContextCall(org.xml.sax.helpers.NamespaceSupport instance) {
		instance.popContext();
	}

	public void processNameCall(java.lang.String a,[Ljava.lang.String; b,boolean c,org.xml.sax.helpers.NamespaceSupport instance) {
		String[] result = instance.processName(a,b,c);
	}

	public void pushContextCall(org.xml.sax.helpers.NamespaceSupport instance) {
		instance.pushContext();
	}

	public void resetCall(org.xml.sax.helpers.NamespaceSupport instance) {
		instance.reset();
	}

	public void setNamespaceDeclUrisCall(boolean a,org.xml.sax.helpers.NamespaceSupport instance) {
		instance.setNamespaceDeclUris(a);
	}

}
