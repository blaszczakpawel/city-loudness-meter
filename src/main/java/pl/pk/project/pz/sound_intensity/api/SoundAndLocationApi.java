package pl.pk.project.pz.sound_intensity.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pk.project.pz.sound_intensity.dao.entity.SoundAndLocation;
import pl.pk.project.pz.sound_intensity.manager.SoundAndLocationManager;
import pl.pk.project.pz.sound_intensity.pojo.FeatureCollection;
import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/location")
public class SoundAndLocationApi {

    private SoundAndLocationManager soundAndLocationManager;

    @Autowired
    public SoundAndLocationApi(SoundAndLocationManager soundAndLocationManager){
        this.soundAndLocationManager=soundAndLocationManager;
    }
    @GetMapping("/all")
    public ResponseEntity<FeatureCollection>getAll(@RequestParam(name = "fromDate",required = false)Long fromDate,
                                                    @RequestParam(name ="toDate",required = false)Long toDate)throws Exception{
        if(fromDate==null&&toDate==null){
        return new ResponseEntity<>(soundAndLocationManager.findAll(), HttpStatus.OK);
    }else{
        return new ResponseEntity<>(soundAndLocationManager.getPointsBetweenDate(fromDate,toDate),HttpStatus.OK);
    }
    }

    @GetMapping
    public ResponseEntity<Optional<SoundAndLocation>> getById(@RequestParam Long id) throws Exception{
        return new ResponseEntity<>(soundAndLocationManager.findById(id),HttpStatus.OK);
    }

    @PostMapping(consumes = (MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<Void> addNewPoint( @RequestBody List<@Valid SoundAndLocation> soundAndLocation) throws Exception{
        soundAndLocationManager.save(soundAndLocation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePoint(@RequestParam Long id) throws Exception {
        soundAndLocationManager.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/last")
    public ResponseEntity<List<FeatureCollection>> getLocation(){
        return new ResponseEntity<>(soundAndLocationManager.getLocation(),HttpStatus.OK);
    }

    @GetMapping("/true")
    public ResponseEntity<Boolean> GetTrue(@RequestParam Long id){
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

}
