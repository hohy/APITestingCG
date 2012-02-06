package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 6.2.12
 * Time: 10:46
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "whitelist")
public class WhitelistRule {

    @XmlEnum
    public enum RuleTarget {
        @XmlEnumValue("extender")
        EXTENDER,
        @XmlEnumValue("implementer")
        IMPLEMENTER,
        @XmlEnumValue("instantiator")
        INSTANTIATOR
    }

    @XmlAttribute
    protected RuleTarget target;

    @XmlValue
    protected String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public RuleTarget getTarget() {
        return target;
    }

    public void setTarget(RuleTarget target) {
        this.target = target;
    }
}
