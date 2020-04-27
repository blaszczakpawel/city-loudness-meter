package pl.pk.project.pz.sound_intensity.dao.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class SoundAndLocation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Range(min=-90,max=90)
    private Double latitude;

    @NotNull
    @Range(min=-180,max=180)
    private Double longitude;


    @NotNull
    @Range(min=0,max=400)
    private Double magnitude;

    @CreationTimestamp
    private LocalDateTime dateTime;

    public SoundAndLocation(Double latitude, Double longitude, Double magnitude, LocalDateTime dateTime){
        this.latitude=latitude;
        this.longitude=longitude;
        this.magnitude = magnitude;

    }
    public SoundAndLocation(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double soundIntensity) {
        this.magnitude = soundIntensity;
    }

}
