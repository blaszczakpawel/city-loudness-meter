package pl.pk.project.pz.sound_intensity.pojo.convert;

import pl.pk.project.pz.sound_intensity.dao.entity.SoundAndLocation;
import pl.pk.project.pz.sound_intensity.pojo.FeatureCollection;
import pl.pk.project.pz.sound_intensity.pojo.Features;
import pl.pk.project.pz.sound_intensity.pojo.Geometry;
import pl.pk.project.pz.sound_intensity.pojo.Properties;

import java.util.ArrayList;
import java.util.List;

public  class convertToFeatureCollection {


    public static FeatureCollection convertToFeatureCollection(Iterable<SoundAndLocation> soundAndLocations){
        List<Features> features=new ArrayList<>();
        for (SoundAndLocation exp:soundAndLocations
             ) {
            Properties properties=new Properties(exp.getMagnitude());
            Double[] tab=new Double[2];
            tab[0]=exp.getLongitude();
            tab[1]=exp.getLatitude();
            Geometry geometry=new Geometry(tab);
            features.add(new Features(properties,geometry));
        }

        return new FeatureCollection(features);
    }
}
