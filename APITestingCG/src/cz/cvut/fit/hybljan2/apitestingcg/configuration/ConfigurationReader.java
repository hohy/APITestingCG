package cz.cvut.fit.hybljan2.apitestingcg.configuration;

import cz.cvut.fit.hybljan2.apitestingcg.APITestingCG;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.ConfigurationReader.ConfigurationKey;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class reads configuration file and provide access to information in it.
 * @author Jan Hýbl
 */
public class ConfigurationReader {
    
    private List<ScannerConfiguration> apiConfigurations = new LinkedList<ScannerConfiguration>();
    private List<ApiViewConfiguration> apiViewConfiguraions = new LinkedList<ApiViewConfiguration>();
    
    public enum ConfigurationKey {
        NAME, VERSION, SOURCE
    }
    
    public void parseConfiguration(String filePath) {
    // Reading configuration from configuration file
    try {
        File xmlConifigFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlConifigFile);
        doc.getDocumentElement().normalize();

        // Get list of API, which will be tested
        NodeList apiNodeList = doc.getElementsByTagName("api");
        // Process that list
        for (int i = 0; i < apiNodeList.getLength(); i++) {
            Node apiNode = apiNodeList.item(i);
            if(apiNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element apiElement = (Element) apiNode;
                ScannerConfiguration sc = new ScannerConfiguration();
                
                // get source of api
                String source = apiElement.getAttribute("source");                
                if(source.equals("sourcecode")) sc.setSource(ScannerConfiguration.APISource.SOURCECODE);
                else if(source.equals("bytecode")) sc.setSource(ScannerConfiguration.APISource.BYTECODE);
                
                // get id
                sc.setId(getElementValue(apiElement, "id"));
                // get api name
                sc.setApiName(getElementValue(apiElement, "name"));
                // get api version
                sc.setApiVersion(getElementValue(apiElement, "version"));
                // get path to api
                sc.setPath(getElementValue(apiElement, "path"));
                // get classpath
                sc.setClasspath(getElementValue(apiElement, "classpath"));
                // get source version
                sc.setSourceVersion(getElementValue(apiElement, "sourceVersion"));
                
                // TODO: add other elements
                
                apiConfigurations.add(sc);
            }
        }
        
        // Get list of APIView forms
        NodeList apiViewNodeList = doc.getElementsByTagName("apiview");
        for (int i = 0; i < apiViewNodeList.getLength(); i++) {
            Node apiViewNode = apiViewNodeList.item(i);
            if(apiViewNode.getNodeType() == Node.ELEMENT_NODE) {
                Element apiViewElement = (Element) apiViewNode;
                ApiViewConfiguration ac = new ApiViewConfiguration();
                
                ac.setApiId(getElementValue(apiViewElement, "apiid"));
                // TODO: add rest of elements
                
                apiViewConfiguraions.add(ac);
            }
            
        }
        
    } catch (SAXException saxexc) {

    } catch (ParserConfigurationException ex) {
        Logger.getLogger(APITestingCG.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ioexc) {

    }    
    }

    private String getElementValue(Element ele, String tagName) {
        String textVal = null;
	NodeList nl = ele.getElementsByTagName(tagName);
	if(nl != null && nl.getLength() > 0) {
            Element el = (Element)nl.item(0);
            if(el.hasChildNodes()) textVal = el.getFirstChild().getNodeValue();
	}
	return textVal;        
    }
    
    public List<ScannerConfiguration> getApiConfigurations() {
        return apiConfigurations;
    }

    public List<ApiViewConfiguration> getApiViewConfigurations() {
        return apiViewConfiguraions;
    }


}
