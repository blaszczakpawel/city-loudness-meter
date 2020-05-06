package pl.pk.project.pz.sound_intensity.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pk.project.pz.sound_intensity.dao.SoundAndLocationRepo;
import pl.pk.project.pz.sound_intensity.dao.entity.SoundAndLocation;
import pl.pk.project.pz.sound_intensity.pojo.FeatureCollection;
import pl.pk.project.pz.sound_intensity.pojo.convert.convertToFeatureCollection;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoundAndLocationManager {

    private SoundAndLocationRepo soundAndLocationRepo;

    @Autowired
    public SoundAndLocationManager(SoundAndLocationRepo soundAndLocationRepo) {
        this.soundAndLocationRepo = soundAndLocationRepo;
    }

    public Optional<SoundAndLocation> findById(Long id){
        return soundAndLocationRepo.findById(id);
    }

    public FeatureCollection findAll(){
        return convertToFeatureCollection.convertToFeatureCollection(soundAndLocationRepo.findAll());
    }

    public SoundAndLocation save(SoundAndLocation soundAndLocation){
        return  soundAndLocationRepo.save(soundAndLocation);
    }

    public void deleteById(Long id){
        soundAndLocationRepo.deleteById(id);
    }

    public List<FeatureCollection> getLocation(){
        List<FeatureCollection> List =new ArrayList<>();
        for(int i=0;i<6;i++) {
            LocalDateTime first=LocalDateTime.now().minusHours(i+1);
            LocalDateTime second=LocalDateTime.now().minusHours(i);
            List.add(convertToFeatureCollection.convertToFeatureCollection(soundAndLocationRepo.findAllByDateTimeBetween(first,second)));
        }
        return List;
    }

    public FeatureCollection getPointsBetweenDate(Long earlier,Long later){
        if(earlier!=null&&later!=null){
            return convertToFeatureCollection.convertToFeatureCollection(soundAndLocationRepo.findAllByDateTimeBetween(new Timestamp(earlier).toLocalDateTime(),new Timestamp(later).toLocalDateTime()));
        }
        else if(earlier==null){
            return convertToFeatureCollection.convertToFeatureCollection(soundAndLocationRepo.findAllByDateTimeBetween(new Timestamp(1577836800).toLocalDateTime(),new Timestamp(later).toLocalDateTime()));
        }
        else if(later==null){
            return convertToFeatureCollection.convertToFeatureCollection(soundAndLocationRepo.findAllByDateTimeBetween(new Timestamp(earlier).toLocalDateTime(),new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        }
        else{
            return findAll();
       }
    }
}
