package org.xml.sax.helpers;

import org.xml.sax.helpers.ParserFactory;

public class ParserFactoryInstantiator {

	/*   CONSTRUCTORS   */

	/*   METHODS   */

	public void makeParserCall() {
		Parser result = org.xml.sax.helpers.ParserFactory.makeParser();
	}

	public void makeParserCall(java.lang.String a) {
		Parser result = org.xml.sax.helpers.ParserFactory.makeParser(a);
	}

}
