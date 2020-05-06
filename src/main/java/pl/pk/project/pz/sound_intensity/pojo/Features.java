package pl.pk.project.pz.sound_intensity.pojo;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Features)) return false;
        Features features = (Features) o;
        return Objects.equals(getType(), features.getType()) &&
                Objects.equals(getProperties(), features.getProperties()) &&
                Objects.equals(getGeometry(), features.getGeometry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getProperties(), getGeometry());
    }
}
