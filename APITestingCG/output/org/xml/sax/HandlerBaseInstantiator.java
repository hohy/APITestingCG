package org.xml.sax;

import org.xml.sax.HandlerBase;

public class HandlerBaseInstantiator {

	/*   CONSTRUCTORS   */

	public HandlerBaseInstantiator () {
		org.xml.sax.HandlerBase instance = new org.xml.sax.HandlerBase();
	}

	/*   METHODS   */

	public void charactersCall([C a,int b,int c,org.xml.sax.HandlerBase instance) {
		instance.characters(a,b,c);
	}

	public void endDocumentCall(org.xml.sax.HandlerBase instance) {
		instance.endDocument();
	}

	public void endElementCall(java.lang.String a,org.xml.sax.HandlerBase instance) {
		instance.endElement(a);
	}

	public void errorCall(org.xml.sax.SAXParseException a,org.xml.sax.HandlerBase instance) {
		instance.error(a);
	}

	public void fatalErrorCall(org.xml.sax.SAXParseException a,org.xml.sax.HandlerBase instance) {
		instance.fatalError(a);
	}

	public void ignorableWhitespaceCall([C a,int b,int c,org.xml.sax.HandlerBase instance) {
		instance.ignorableWhitespace(a,b,c);
	}

	public void notationDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.HandlerBase instance) {
		instance.notationDecl(a,b,c);
	}

	public void processingInstructionCall(java.lang.String a,java.lang.String b,org.xml.sax.HandlerBase instance) {
		instance.processingInstruction(a,b);
	}

	public void resolveEntityCall(java.lang.String a,java.lang.String b,org.xml.sax.HandlerBase instance) {
		InputSource result = instance.resolveEntity(a,b);
	}

	public void setDocumentLocatorCall(org.xml.sax.Locator a,org.xml.sax.HandlerBase instance) {
		instance.setDocumentLocator(a);
	}

	public void startDocumentCall(org.xml.sax.HandlerBase instance) {
		instance.startDocument();
	}

	public void startElementCall(java.lang.String a,org.xml.sax.AttributeList b,org.xml.sax.HandlerBase instance) {
		instance.startElement(a,b);
	}

	public void unparsedEntityDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,java.lang.String d,org.xml.sax.HandlerBase instance) {
		instance.unparsedEntityDecl(a,b,c,d);
	}

	public void warningCall(org.xml.sax.SAXParseException a,org.xml.sax.HandlerBase instance) {
		instance.warning(a);
	}

}
