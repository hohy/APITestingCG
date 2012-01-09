package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 9.1.12
 * Time: 14:46
 */
@XmlRootElement(name = "generator")
public class GeneratorConfiguration {
    
    private String instanceIdentifier = "instance";
    private String methodCallIdentifier = "%s";
    private String methodNullCallIdentifier = "%sNullCall";
    private String instantiatorClassIdentifier = "%sInstantiator";
    private String extenderClassIdentifier = "%sExtender";
    private String implementerClassIdentifier = "%sImplementer";

    @XmlElement(name = "instance-identifier")
    public String getInstanceIdentifier() {
        return instanceIdentifier;
    }

    public void setInstanceIdentifier(String instanceIdentifier) {
        this.instanceIdentifier = instanceIdentifier;
    }

    @XmlElement(name = "method-call-identifier")
    public String getMethodCallIdentifier() {
        return methodCallIdentifier;
    }

    public void setMethodCallIdentifier(String methodCallIdentifier) {
        this.methodCallIdentifier = methodCallIdentifier;
    }

    @XmlElement(name = "method-null-call-identifier")
    public String getMethodNullCallIdentifier() {
        return methodNullCallIdentifier;
    }

    public void setMethodNullCallIdentifier(String methodNullCallIdentifier) {
        this.methodNullCallIdentifier = methodNullCallIdentifier;
    }

    @XmlElement(name = "instantiatior-identifier")
    public String getInstantiatorClassIdentifier() {
        return instantiatorClassIdentifier;
    }

    public void setInstantiatorClassIdentifier(String instantiatorClassIdentifier) {
        this.instantiatorClassIdentifier = instantiatorClassIdentifier;
    }

    @XmlElement(name = "extender-identifier")
    public String getExtenderClassIdentifier() {
        return extenderClassIdentifier;
    }

    public void setExtenderClassIdentifier(String extenderClassIdentifier) {
        this.extenderClassIdentifier = extenderClassIdentifier;
    }

    @XmlElement(name = "implementer-identifier")
    public String getImplementerClassIdentifier() {
        return implementerClassIdentifier;
    }

    public void setImplementerClassIdentifier(String implementerClassIdentifier) {
        this.implementerClassIdentifier = implementerClassIdentifier;
    }
}
