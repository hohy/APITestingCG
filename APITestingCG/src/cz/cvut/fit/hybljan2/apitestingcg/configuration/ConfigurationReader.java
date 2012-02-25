package cz.cvut.fit.hybljan2.apitestingcg.configuration;

import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * This class reads configuration file and provide access to information in it.
 *
 * @author Jan Hýbl
 */
public class ConfigurationReader {


    public enum ConfigurationKey {
        NAME, VERSION, SOURCE
    }

    public Configuration parseConfiguration(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // skip validation
            unmarshaller.setSchema(null);
            Configuration configuration = (Configuration) unmarshaller.unmarshal(new File(filePath));
            return configuration;
        } catch (JAXBException e) {
            System.out.println("Configuration read error.");
            e.printStackTrace();
        }
        return null;
    }
}
