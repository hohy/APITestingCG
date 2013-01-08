/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Developmen and Distribution License (CDDL). You can obtain a copy of the CDDL at http://www.netbeans.org/cddl.html.
 */

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
    private List<BlacklistRule> blacklistRules = new LinkedList<BlacklistRule>();
    private boolean skipDeprecated = false;

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

    public void addWhitelistRule(WhitelistRule rule) {
        getWhitelistRules().add(rule);
    }

    @XmlElement(name = "blacklist")
    public List<BlacklistRule> getBlacklistRules() {
        return blacklistRules;
    }

    public void setBlacklistRules(List<BlacklistRule> blacklistRules) {
        this.blacklistRules = blacklistRules;
    }

    public void addBlackListRule(BlacklistRule rule) {
        blacklistRules.add(rule);
    }

    @XmlElement(name = "skip-deprecated")
    public boolean isSkipDeprecated() {
        return skipDeprecated;
    }

    public void setSkipDeprecated(boolean skipDeprecated) {
        this.skipDeprecated = skipDeprecated;
    }
}
