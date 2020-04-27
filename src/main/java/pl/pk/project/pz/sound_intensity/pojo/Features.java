package pl.pk.project.pz.sound_intensity.pojo;


public class Features {
    private final String Type="Feature";
    private Properties properties;
    private Geometry geometry;

    public Properties getProperties() {
        return properties;
    }

    public Features(Properties properties, Geometry geometry) {
        this.properties = properties;
        this.geometry = geometry;
    }
    public Features(){}

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getType() {
        return Type;
    }
}
