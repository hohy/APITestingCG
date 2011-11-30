package org.xml.sax.helpers;

import org.xml.sax.helpers.XMLFilterImpl;

public class XMLFilterImplInstantiator {

	/*   CONSTRUCTORS   */

	public XMLFilterImplInstantiator () {
		org.xml.sax.helpers.XMLFilterImpl instance = new org.xml.sax.helpers.XMLFilterImpl();
	}

	public XMLFilterImplInstantiator (org.xml.sax.helpers.XMLFilterImpl a) {
		org.xml.sax.helpers.XMLFilterImpl instance = new org.xml.sax.helpers.XMLFilterImpl(a);
		org.xml.sax.helpers.XMLFilterImpl nullinstance = new org.xml.sax.helpers.XMLFilterImpl(null);
	}

	/*   METHODS   */

	public void charactersCall([C a,int b,int c,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.characters(a,b,c);
	}

	public void endDocumentCall(org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.endDocument();
	}

	public void endElementCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.endElement(a,b,c);
	}

	public void endPrefixMappingCall(java.lang.String a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.endPrefixMapping(a);
	}

	public void errorCall(org.xml.sax.SAXParseException a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.error(a);
	}

	public void fatalErrorCall(org.xml.sax.SAXParseException a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.fatalError(a);
	}

	public void getContentHandlerCall(org.xml.sax.helpers.XMLFilterImpl instance) {
		ContentHandler result = instance.getContentHandler();
	}

	public void getDTDHandlerCall(org.xml.sax.helpers.XMLFilterImpl instance) {
		DTDHandler result = instance.getDTDHandler();
	}

	public void getEntityResolverCall(org.xml.sax.helpers.XMLFilterImpl instance) {
		EntityResolver result = instance.getEntityResolver();
	}

	public void getErrorHandlerCall(org.xml.sax.helpers.XMLFilterImpl instance) {
		ErrorHandler result = instance.getErrorHandler();
	}

	public void getFeatureCall(java.lang.String a,org.xml.sax.helpers.XMLFilterImpl instance) {
		boolean result = instance.getFeature(a);
	}

	public void getParentCall(org.xml.sax.helpers.XMLFilterImpl instance) {
		XMLReader result = instance.getParent();
	}

	public void getPropertyCall(java.lang.String a,org.xml.sax.helpers.XMLFilterImpl instance) {
		Object result = instance.getProperty(a);
	}

	public void ignorableWhitespaceCall([C a,int b,int c,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.ignorableWhitespace(a,b,c);
	}

	public void notationDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.notationDecl(a,b,c);
	}

	public void parseCall(java.lang.String a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.parse(a);
	}

	public void parseCall(org.xml.sax.InputSource a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.parse(a);
	}

	public void processingInstructionCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.processingInstruction(a,b);
	}

	public void resolveEntityCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.XMLFilterImpl instance) {
		InputSource result = instance.resolveEntity(a,b);
	}

	public void setContentHandlerCall(org.xml.sax.ContentHandler a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setContentHandler(a);
	}

	public void setDTDHandlerCall(org.xml.sax.DTDHandler a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setDTDHandler(a);
	}

	public void setDocumentLocatorCall(org.xml.sax.Locator a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setDocumentLocator(a);
	}

	public void setEntityResolverCall(org.xml.sax.EntityResolver a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setEntityResolver(a);
	}

	public void setErrorHandlerCall(org.xml.sax.ErrorHandler a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setErrorHandler(a);
	}

	public void setFeatureCall(java.lang.String a,boolean b,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setFeature(a,b);
	}

	public void setParentCall(org.xml.sax.XMLReader a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setParent(a);
	}

	public void setPropertyCall(java.lang.String a,java.lang.Object b,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.setProperty(a,b);
	}

	public void skippedEntityCall(java.lang.String a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.skippedEntity(a);
	}

	public void startDocumentCall(org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.startDocument();
	}

	public void startElementCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.Attributes d,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.startElement(a,b,c,d);
	}

	public void startPrefixMappingCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.startPrefixMapping(a,b);
	}

	public void unparsedEntityDeclCall(java.lang.String a,java.lang.String b,java.lang.String c,java.lang.String d,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.unparsedEntityDecl(a,b,c,d);
	}

	public void warningCall(org.xml.sax.SAXParseException a,org.xml.sax.helpers.XMLFilterImpl instance) {
		instance.warning(a);
	}

}
