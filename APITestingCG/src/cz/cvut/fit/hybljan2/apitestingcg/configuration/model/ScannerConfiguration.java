/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Developmen and Distribution License (CDDL). You can obtain a copy of the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.*;

/**
 * Class stores information how to scan API and other information about API for
 * scanner
 *
 * @author Jan Hybl
 */
@XmlRootElement(name = "api")
public class ScannerConfiguration {

    @XmlEnum
    public enum APISource {
        @XmlEnumValue("sourcecode")
        SOURCECODE,
        @XmlEnumValue("bytecode")
        BYTECODE
    }

    private APISource source;
    private String apiName = "";
    private String apiVersion = "";
    private String path = "";
    private String classpath = " ";
    private String sourceVersion = "1.7";
    private String id = "";

    @XmlElement(name = "id", required = true)
    public String getId() {
        if (id != null) return id;
        else return apiName + " " + apiVersion + " " + source;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(required = true)
    public APISource getSource() {
        return source;
    }

    public void setSource(APISource source) {
        this.source = source;
    }

    @XmlElement(name = "name")
    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    @XmlElement(name = "version")
    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @XmlElement
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @XmlElement
    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    @XmlElement
    public String getSourceVersion() {
        return sourceVersion;
    }

    public void setSourceVersion(String sourceVersion) {
        this.sourceVersion = sourceVersion;
    }


}
