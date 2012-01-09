package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 5.1.12
 * Time: 13:57
 */

@XmlRootElement(name = "configuration")
public class Configuration {

    @XmlElement(name = "apiview")
    List<ApiViewConfiguration> viewConfigurations = new LinkedList<ApiViewConfiguration>();

    @XmlElement(name = "api")
    List<ScannerConfiguration> apiConfigurations = new LinkedList<ScannerConfiguration>();
    
    @XmlElement(name = "generate")
    List<GeneratorConfiguration> generatorConfigurations = new LinkedList<GeneratorConfiguration>();

    public List<GeneratorConfiguration> getGeneratorConfigurations() {
        return generatorConfigurations;
    }

    public List<ApiViewConfiguration> getViewConfigurations() {
        return viewConfigurations;
    }

    public List<ScannerConfiguration> getApiConfigurations() {
        return apiConfigurations;
    }
}
