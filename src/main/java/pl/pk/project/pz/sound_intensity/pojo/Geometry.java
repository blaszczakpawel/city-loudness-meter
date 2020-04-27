package pl.pk.project.pz.sound_intensity.pojo;

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
}
