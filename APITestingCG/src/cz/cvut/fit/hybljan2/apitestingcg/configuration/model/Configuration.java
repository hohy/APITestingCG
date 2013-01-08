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
 * Time: 13:57
 */

@XmlRootElement(name = "configuration")
public class Configuration {

    @XmlElement(name = "apiview")
    List<ApiViewConfiguration> viewConfigurations = new LinkedList<ApiViewConfiguration>();

    @XmlElement(name = "api")
    List<ScannerConfiguration> apiConfigurations = new LinkedList<ScannerConfiguration>();

    @XmlElement(name = "generate")
    List<GeneratorJobConfiguration> generatorJobConfigurations = new LinkedList<GeneratorJobConfiguration>();

    @XmlElement(name = "generator")
    GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();

    public List<GeneratorJobConfiguration> getGeneratorJobConfigurations() {
        return generatorJobConfigurations;
    }

    public List<ApiViewConfiguration> getViewConfigurations() {
        return viewConfigurations;
    }

    public List<ScannerConfiguration> getApiConfigurations() {
        return apiConfigurations;
    }

    public GeneratorConfiguration getGeneratorConfiguration() {
        return generatorConfiguration;
    }
}
