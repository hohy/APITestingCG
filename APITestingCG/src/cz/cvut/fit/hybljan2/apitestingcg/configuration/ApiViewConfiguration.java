package cz.cvut.fit.hybljan2.apitestingcg.configuration;

/**
 * Class store configuration for ApiView window.
 * @author Jan Hybl
 */
public class ApiViewConfiguration {
    private String apiId = "";
    private int widht;
    private int height;
    private int x;
    private int y;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidht() {
        return widht;
    }

    public void setWidht(int widht) {
        this.widht = widht;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
        
}
