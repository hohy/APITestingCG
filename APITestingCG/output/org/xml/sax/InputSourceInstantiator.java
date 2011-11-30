package org.xml.sax;

import org.xml.sax.InputSource;

public class InputSourceInstantiator {

	/*   CONSTRUCTORS   */

	public InputSourceInstantiator () {
		org.xml.sax.InputSource instance = new org.xml.sax.InputSource();
	}

	public InputSourceInstantiator (org.xml.sax.InputSource a) {
		org.xml.sax.InputSource instance = new org.xml.sax.InputSource(a);
		org.xml.sax.InputSource nullinstance = new org.xml.sax.InputSource(null);
	}

	/*   METHODS   */

	public void getByteStreamCall(org.xml.sax.InputSource instance) {
		InputStream result = instance.getByteStream();
	}

	public void getCharacterStreamCall(org.xml.sax.InputSource instance) {
		Reader result = instance.getCharacterStream();
	}

	public void getEncodingCall(org.xml.sax.InputSource instance) {
		String result = instance.getEncoding();
	}

	public void getPublicIdCall(org.xml.sax.InputSource instance) {
		String result = instance.getPublicId();
	}

	public void getSystemIdCall(org.xml.sax.InputSource instance) {
		String result = instance.getSystemId();
	}

	public void setByteStreamCall(java.io.InputStream a,org.xml.sax.InputSource instance) {
		instance.setByteStream(a);
	}

	public void setCharacterStreamCall(java.io.Reader a,org.xml.sax.InputSource instance) {
		instance.setCharacterStream(a);
	}

	public void setEncodingCall(java.lang.String a,org.xml.sax.InputSource instance) {
		instance.setEncoding(a);
	}

	public void setPublicIdCall(java.lang.String a,org.xml.sax.InputSource instance) {
		instance.setPublicId(a);
	}

	public void setSystemIdCall(java.lang.String a,org.xml.sax.InputSource instance) {
		instance.setSystemId(a);
	}

}
