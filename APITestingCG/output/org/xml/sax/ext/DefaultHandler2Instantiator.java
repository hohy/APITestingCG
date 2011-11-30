package org.xml.sax.ext;

import org.xml.sax.ext.DefaultHandler2;

public class DefaultHandler2Instantiator {

	/*   CONSTRUCTORS   */

	public DefaultHandler2Instantiator () {
		org.xml.sax.ext.DefaultHandler2 instance = new org.xml.sax.ext.DefaultHandler2();
	}

	/*   METHODS   */

	public void attributeDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,java.lang.String d,java.lang.String e,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.attributeDecl(a,b,c,d,e);
	}

	public void commentCall([C a,int b,int c,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.comment(a,b,c);
	}

	public void elementDeclCall(java.lang.String a,java.lang.String b,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.elementDecl(a,b);
	}

	public void endCDATACall(org.xml.sax.ext.DefaultHandler2 instance) {
		instance.endCDATA();
	}

	public void endDTDCall(org.xml.sax.ext.DefaultHandler2 instance) {
		instance.endDTD();
	}

	public void endEntityCall(java.lang.String a,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.endEntity(a);
	}

	public void externalEntityDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.externalEntityDecl(a,b,c);
	}

	public void getExternalSubsetCall(java.lang.String a,java.lang.String b,org.xml.sax.ext.DefaultHandler2 instance) {
		InputSource result = instance.getExternalSubset(a,b);
	}

	public void internalEntityDeclCall(java.lang.String a,java.lang.String b,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.internalEntityDecl(a,b);
	}

	public void resolveEntityCall(java.lang.String a,java.lang.String b,java.lang.String c,java.lang.String d,org.xml.sax.ext.DefaultHandler2 instance) {
		InputSource result = instance.resolveEntity(a,b,c,d);
	}

	public void resolveEntityCall(java.lang.String a,java.lang.String b,org.xml.sax.ext.DefaultHandler2 instance) {
		InputSource result = instance.resolveEntity(a,b);
	}

	public void startCDATACall(org.xml.sax.ext.DefaultHandler2 instance) {
		instance.startCDATA();
	}

	public void startDTDCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.startDTD(a,b,c);
	}

	public void startEntityCall(java.lang.String a,org.xml.sax.ext.DefaultHandler2 instance) {
		instance.startEntity(a);
	}

}
