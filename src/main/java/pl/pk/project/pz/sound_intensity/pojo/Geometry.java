package pl.pk.project.pz.sound_intensity.pojo;

import java.util.Arrays;
import java.util.Objects;

public class Geometry {
    private final String type="Point";
    private Double coordinates[];

    public Geometry(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Geometry(){}

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geometry)) return false;
        Geometry geometry = (Geometry) o;
        return Objects.equals(getType(), geometry.getType()) &&
                Arrays.equals(getCoordinates(), geometry.getCoordinates());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getType());
        result = 31 * result + Arrays.hashCode(getCoordinates());
        return result;
    }
}
