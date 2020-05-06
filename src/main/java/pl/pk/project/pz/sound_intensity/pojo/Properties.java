package pl.pk.project.pz.sound_intensity.pojo;

import java.util.Objects;

public class Properties {
    private Double magnitude;

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }
    public Properties(){}

    public Properties(Double magnitude) {
        this.magnitude = magnitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Properties)) return false;
        Properties that = (Properties) o;
        return Objects.equals(getMagnitude(), that.getMagnitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMagnitude());
    }
}
