package pl.pk.project.pz.sound_intensity.pojo;

import java.util.List;
import java.util.Objects;

public class FeatureCollection {
    private final String type="FeatureCollection";
    private List<Features> features;

    public FeatureCollection(List<Features> features) {
        this.features = features;
    }

    public FeatureCollection(){}

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeatureCollection)) return false;
        FeatureCollection that = (FeatureCollection) o;
        return Objects.equals(getType(), that.getType()) &&
                Objects.equals(getFeatures(), that.getFeatures());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getFeatures());
    }
}
