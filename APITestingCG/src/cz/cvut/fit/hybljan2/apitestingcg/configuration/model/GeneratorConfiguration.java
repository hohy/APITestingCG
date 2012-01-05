package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 5.1.12
 * Time: 15:19
 */
@XmlRootElement(name = "generate")
public class GeneratorConfiguration {
    private String apiId;
    private String outputDir;

    @XmlElement(name = "id", required = true)
    public String getApiId() {
        return apiId;
    }

    @XmlElement(name = "output")
    public String getOutputDir() {
        return outputDir;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
}
