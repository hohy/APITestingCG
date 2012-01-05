package cz.cvut.fit.hybljan2.apitestingcg.configuration;

import cz.cvut.fit.hybljan2.apitestingcg.APITestingCG;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ApiViewConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.Configuration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
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
    
    private Configuration configuration;

    
    public enum ConfigurationKey {
        NAME, VERSION, SOURCE
    }
    
    public void parseConfiguration(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // skip validation
            unmarshaller.setSchema(null);
            configuration = (Configuration) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            System.out.println("Configuration read error.");
            e.printStackTrace();
        }

    }
    
    public List<ScannerConfiguration> getApiConfigurations() {
        return configuration.getApiConfigurations();
    }

    public List<ApiViewConfiguration> getApiViewConfigurations() {
        return configuration.getViewConfigurations();
    }


}
