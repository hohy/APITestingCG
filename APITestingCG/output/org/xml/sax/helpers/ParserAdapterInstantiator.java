package org.xml.sax.helpers;

import org.xml.sax.helpers.ParserAdapter;

public class ParserAdapterInstantiator {

	/*   CONSTRUCTORS   */

	public ParserAdapterInstantiator () {
		org.xml.sax.helpers.ParserAdapter instance = new org.xml.sax.helpers.ParserAdapter();
	}

	public ParserAdapterInstantiator (org.xml.sax.helpers.ParserAdapter a) {
		org.xml.sax.helpers.ParserAdapter instance = new org.xml.sax.helpers.ParserAdapter(a);
		org.xml.sax.helpers.ParserAdapter nullinstance = new org.xml.sax.helpers.ParserAdapter(null);
	}

	/*   METHODS   */

	public void charactersCall([C a,int b,int c,org.xml.sax.helpers.ParserAdapter instance) {
		instance.characters(a,b,c);
	}

	public void endDocumentCall(org.xml.sax.helpers.ParserAdapter instance) {
		instance.endDocument();
	}

	public void endElementCall(java.lang.String a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.endElement(a);
	}

	public void getContentHandlerCall(org.xml.sax.helpers.ParserAdapter instance) {
		ContentHandler result = instance.getContentHandler();
	}

	public void getDTDHandlerCall(org.xml.sax.helpers.ParserAdapter instance) {
		DTDHandler result = instance.getDTDHandler();
	}

	public void getEntityResolverCall(org.xml.sax.helpers.ParserAdapter instance) {
		EntityResolver result = instance.getEntityResolver();
	}

	public void getErrorHandlerCall(org.xml.sax.helpers.ParserAdapter instance) {
		ErrorHandler result = instance.getErrorHandler();
	}

	public void getFeatureCall(java.lang.String a,org.xml.sax.helpers.ParserAdapter instance) {
		boolean result = instance.getFeature(a);
	}

	public void getPropertyCall(java.lang.String a,org.xml.sax.helpers.ParserAdapter instance) {
		Object result = instance.getProperty(a);
	}

	public void ignorableWhitespaceCall([C a,int b,int c,org.xml.sax.helpers.ParserAdapter instance) {
		instance.ignorableWhitespace(a,b,c);
	}

	public void parseCall(java.lang.String a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.parse(a);
	}

	public void parseCall(org.xml.sax.InputSource a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.parse(a);
	}

	public void processingInstructionCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.ParserAdapter instance) {
		instance.processingInstruction(a,b);
	}

	public void setContentHandlerCall(org.xml.sax.ContentHandler a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.setContentHandler(a);
	}

	public void setDTDHandlerCall(org.xml.sax.DTDHandler a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.setDTDHandler(a);
	}

	public void setDocumentLocatorCall(org.xml.sax.Locator a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.setDocumentLocator(a);
	}

	public void setEntityResolverCall(org.xml.sax.EntityResolver a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.setEntityResolver(a);
	}

	public void setErrorHandlerCall(org.xml.sax.ErrorHandler a,org.xml.sax.helpers.ParserAdapter instance) {
		instance.setErrorHandler(a);
	}

	public void setFeatureCall(java.lang.String a,boolean b,org.xml.sax.helpers.ParserAdapter instance) {
		instance.setFeature(a,b);
	}

	public void setPropertyCall(java.lang.String a,java.lang.Object b,org.xml.sax.helpers.ParserAdapter instance) {
		instance.setProperty(a,b);
	}

	public void startDocumentCall(org.xml.sax.helpers.ParserAdapter instance) {
		instance.startDocument();
	}

	public void startElementCall(java.lang.String a,org.xml.sax.AttributeList b,org.xml.sax.helpers.ParserAdapter instance) {
		instance.startElement(a,b);
	}

}
