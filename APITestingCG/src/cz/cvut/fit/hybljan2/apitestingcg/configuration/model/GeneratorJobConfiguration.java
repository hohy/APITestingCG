package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 5.1.12
 * Time: 15:19
 */
@XmlRootElement(name = "generate")
public class GeneratorJobConfiguration {
    private String apiId;
    private String outputDir = "output";
    private String outputPackage = "test.%s";
    private List<WhitelistRule> whitelistRules = new LinkedList<WhitelistRule>();

    @XmlElement(name = "id", required = true)
    public String getApiId() {
        return apiId;
    }

    @XmlElement(name = "output-directory")
    public String getOutputDir() {
        return outputDir;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    @XmlElement(name = "output-package")
    public String getOutputPackage() {
        return outputPackage;
    }

    public void setOutputPackage(String outputPackage) {
        this.outputPackage = outputPackage;
    }

    @XmlElement(name = "whitelist")
    public List<WhitelistRule> getWhitelistRules() {
        return whitelistRules;
    }

    public void setWhitelistRules(List<WhitelistRule> whitelistRules) {
        this.whitelistRules = whitelistRules;
    }
}
