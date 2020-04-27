package pl.pk.project.pz.sound_intensity.pojo;

import java.util.List;

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
}
