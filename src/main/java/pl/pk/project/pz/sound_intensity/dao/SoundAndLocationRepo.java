package pl.pk.project.pz.sound_intensity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pk.project.pz.sound_intensity.dao.entity.SoundAndLocation;
import java.time.LocalDateTime;

@Repository
public interface SoundAndLocationRepo extends JpaRepository<SoundAndLocation,Long> {

    Iterable<SoundAndLocation> findAllByDateTimeBetween(LocalDateTime first, LocalDateTime second);

}
