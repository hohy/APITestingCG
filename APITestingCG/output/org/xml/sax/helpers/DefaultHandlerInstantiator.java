package org.xml.sax.helpers;

import org.xml.sax.helpers.DefaultHandler;

public class DefaultHandlerInstantiator {

	/*   CONSTRUCTORS   */

	public DefaultHandlerInstantiator () {
		org.xml.sax.helpers.DefaultHandler instance = new org.xml.sax.helpers.DefaultHandler();
	}

	/*   METHODS   */

	public void charactersCall([C a,int b,int c,org.xml.sax.helpers.DefaultHandler instance) {
		instance.characters(a,b,c);
	}

	public void endDocumentCall(org.xml.sax.helpers.DefaultHandler instance) {
		instance.endDocument();
	}

	public void endElementCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.helpers.DefaultHandler instance) {
		instance.endElement(a,b,c);
	}

	public void endPrefixMappingCall(java.lang.String a,org.xml.sax.helpers.DefaultHandler instance) {
		instance.endPrefixMapping(a);
	}

	public void errorCall(org.xml.sax.SAXParseException a,org.xml.sax.helpers.DefaultHandler instance) {
		instance.error(a);
	}

	public void fatalErrorCall(org.xml.sax.SAXParseException a,org.xml.sax.helpers.DefaultHandler instance) {
		instance.fatalError(a);
	}

	public void ignorableWhitespaceCall([C a,int b,int c,org.xml.sax.helpers.DefaultHandler instance) {
		instance.ignorableWhitespace(a,b,c);
	}

	public void notationDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.helpers.DefaultHandler instance) {
		instance.notationDecl(a,b,c);
	}

	public void processingInstructionCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.DefaultHandler instance) {
		instance.processingInstruction(a,b);
	}

	public void resolveEntityCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.DefaultHandler instance) {
		InputSource result = instance.resolveEntity(a,b);
	}

	public void setDocumentLocatorCall(org.xml.sax.Locator a,org.xml.sax.helpers.DefaultHandler instance) {
		instance.setDocumentLocator(a);
	}

	public void skippedEntityCall(java.lang.String a,org.xml.sax.helpers.DefaultHandler instance) {
		instance.skippedEntity(a);
	}

	public void startDocumentCall(org.xml.sax.helpers.DefaultHandler instance) {
		instance.startDocument();
	}

	public void startElementCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.Attributes d,org.xml.sax.helpers.DefaultHandler instance) {
		instance.startElement(a,b,c,d);
	}

	public void startPrefixMappingCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.DefaultHandler instance) {
		instance.startPrefixMapping(a,b);
	}

	public void unparsedEntityDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,java.lang.String d,org.xml.sax.helpers.DefaultHandler instance) {
		instance.unparsedEntityDecl(a,b,c,d);
	}

	public void warningCall(org.xml.sax.SAXParseException a,org.xml.sax.helpers.DefaultHandler instance) {
		instance.warning(a);
	}

}
