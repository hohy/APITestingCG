package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class store configuration for ApiView window.
 *
 * @author Jan Hybl
 */
@XmlRootElement(name = "apiview")
public class ApiViewConfiguration {
    private String apiId = "";
    private int widht;
    private int height;
    private int x;
    private int y;

    @XmlElement(name = "apiid", required = true)
    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    @XmlElement
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @XmlElement
    public int getWidht() {
        return widht;
    }

    public void setWidht(int widht) {
        this.widht = widht;
    }

    @XmlElement
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @XmlElement
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
