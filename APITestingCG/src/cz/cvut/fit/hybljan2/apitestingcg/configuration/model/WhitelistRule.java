/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Developmen and Distribution License (CDDL). You can obtain a copy of the CDDL at http://www.netbeans.org/cddl.html.
 */

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

    public WhitelistRule() {
    }

    public WhitelistRule(String rule) {
        item = RuleItem.ALL;
        this.rule = rule;
    }

    @XmlEnum
    public enum RuleItem {
        @XmlEnumValue("extender")
        EXTENDER,
        @XmlEnumValue("implementer")
        IMPLEMENTER,
        @XmlEnumValue("instantiator")
        INSTANTIATOR,
        @XmlEnumValue("annotation")
        ANNOTATION,
        @XmlEnumValue("all")
        ALL,
        @XmlEnumValue("null-call")
        NULLCALL
    }

    @XmlAttribute
    protected RuleItem item = RuleItem.ALL;

    @XmlValue
    protected String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public RuleItem getItem() {
        return item;
    }

    public void setItem(RuleItem item) {
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
