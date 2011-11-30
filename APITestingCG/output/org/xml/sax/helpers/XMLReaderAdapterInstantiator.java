package org.xml.sax.helpers;

import org.xml.sax.helpers.XMLReaderAdapter;

public class XMLReaderAdapterInstantiator {

	/*   CONSTRUCTORS   */

	public XMLReaderAdapterInstantiator () {
		org.xml.sax.helpers.XMLReaderAdapter instance = new org.xml.sax.helpers.XMLReaderAdapter();
	}

	public XMLReaderAdapterInstantiator (org.xml.sax.helpers.XMLReaderAdapter a) {
		org.xml.sax.helpers.XMLReaderAdapter instance = new org.xml.sax.helpers.XMLReaderAdapter(a);
		org.xml.sax.helpers.XMLReaderAdapter nullinstance = new org.xml.sax.helpers.XMLReaderAdapter(null);
	}

	/*   METHODS   */

	public void charactersCall([C a,int b,int c,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.characters(a,b,c);
	}

	public void endDocumentCall(org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.endDocument();
	}

	public void endElementCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.endElement(a,b,c);
	}

	public void endPrefixMappingCall(java.lang.String a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.endPrefixMapping(a);
	}

	public void ignorableWhitespaceCall([C a,int b,int c,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.ignorableWhitespace(a,b,c);
	}

	public void parseCall(java.lang.String a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.parse(a);
	}

	public void parseCall(org.xml.sax.InputSource a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.parse(a);
	}

	public void processingInstructionCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.processingInstruction(a,b);
	}

	public void setDTDHandlerCall(org.xml.sax.DTDHandler a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.setDTDHandler(a);
	}

	public void setDocumentHandlerCall(org.xml.sax.DocumentHandler a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.setDocumentHandler(a);
	}

	public void setDocumentLocatorCall(org.xml.sax.Locator a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.setDocumentLocator(a);
	}

	public void setEntityResolverCall(org.xml.sax.EntityResolver a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.setEntityResolver(a);
	}

	public void setErrorHandlerCall(org.xml.sax.ErrorHandler a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.setErrorHandler(a);
	}

	public void setLocaleCall(java.util.Locale a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.setLocale(a);
	}

	public void skippedEntityCall(java.lang.String a,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.skippedEntity(a);
	}

	public void startDocumentCall(org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.startDocument();
	}

	public void startElementCall(java.lang.String a,java.lang.String b,java.lang.String c,org.xml.sax.Attributes d,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.startElement(a,b,c,d);
	}

	public void startPrefixMappingCall(java.lang.String a,java.lang.String b,org.xml.sax.helpers.XMLReaderAdapter instance) {
		instance.startPrefixMapping(a,b);
	}

}
