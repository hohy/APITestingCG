package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 15.2.12
 * Time: 10:15
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "blacklist")
public class BlacklistRule {

    public BlacklistRule(String rule) {
        item = WhitelistRule.RuleItem.ALL;
        this.rule = rule;
    }

    public BlacklistRule() {
    }

    @XmlAttribute
    protected WhitelistRule.RuleItem item = WhitelistRule.RuleItem.ALL;

    @XmlValue
    protected String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public WhitelistRule.RuleItem getItem() {
        return item;
    }

    public void setItem(WhitelistRule.RuleItem item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WhitelistRule that = (WhitelistRule) o;

        if (item != that.item) return false;
        if (rule != null ? !rule.equals(that.rule) : that.rule != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WhitelistRule{" +
                "item=" + item +
                ", rule='" + rule + '\'' +
                '}';
    }
}
