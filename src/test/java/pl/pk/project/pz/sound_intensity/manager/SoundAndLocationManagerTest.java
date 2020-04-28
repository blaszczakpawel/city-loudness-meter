package pl.pk.project.pz.sound_intensity.manager;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pk.project.pz.sound_intensity.dao.SoundAndLocationRepo;
import pl.pk.project.pz.sound_intensity.dao.entity.SoundAndLocation;
import pl.pk.project.pz.sound_intensity.pojo.FeatureCollection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.*;
import pl.pk.project.pz.sound_intensity.pojo.convert.convertToFeatureCollection;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class SoundAndLocationManagerTest {

    @Mock
    SoundAndLocationRepo soundAndLocationRepo;

    @InjectMocks
    SoundAndLocationManager soundAndLocationManager;

    @BeforeAll
    public void setUp(){
        System.out.println("SoundAndLocationManagerTest started");
    }

    @AfterAll
    public void tearDown(){
        System.out.println("SoundAndLocationManagerTest finished");
    }

    @Test
    public void findById_IdEqualsAnyLong() {

        //Get any id to test
        final Long id = anyLong();

        //SoundAndLocation Object for test
        final SoundAndLocation soundAndLocation = new SoundAndLocation();
        soundAndLocation.setId(id);

        //given
        given(soundAndLocationRepo.findById(id)).willReturn(java.util.Optional.of(soundAndLocation));
        //when
        Optional expectedResult =  soundAndLocationManager.findById(id);
        //then
        verify(soundAndLocationRepo).findById(id);
        assertEquals(expectedResult,java.util.Optional.of(soundAndLocation));
    }

    @Test
    public void findById_IdIsNegative(){

        //Get id to test
        final Long id = -2L;

        //SoundAndLocation Object for test
        final SoundAndLocation soundAndLocation = new SoundAndLocation();
        soundAndLocation.setId(id);

        //given
        given(soundAndLocationRepo.findById(id)).willReturn(Optional.empty());
        //when
        Optional expectedResult =  soundAndLocationManager.findById(id);
        //then
        verify(soundAndLocationRepo).findById(id);
        assertEquals(expectedResult,Optional.empty());

    }

    @Test
    public void findAll_WithFourElements() {

        //Data for SoundAndLocation Objects
        double latitude = 10.0;
        double longitude = 20.0;
        double magnitude = 30.0;

        // Test List
        List<SoundAndLocation> soundAndLocationList = new ArrayList<>();

        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));
        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));
        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));
        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));

        FeatureCollection featureCollection = convertToFeatureCollection.convertToFeatureCollection(soundAndLocationList);

        //given
        given(soundAndLocationRepo.findAll()).willReturn(soundAndLocationList);
        //when
        FeatureCollection featureCollectionResult = soundAndLocationManager.findAll();
        //then
        verify(soundAndLocationRepo).findAll();
        assertEquals(featureCollectionResult,featureCollection);

    }

    @Test
    public void findAll_EmptyList(){

        FeatureCollection featureCollection = new FeatureCollection(Collections.emptyList());

        //given
        given(soundAndLocationRepo.findAll()).willReturn(Collections.emptyList());
        //when
        FeatureCollection featureCollectionResult = soundAndLocationManager.findAll();
        //then
        verify(soundAndLocationRepo).findAll();
        assertEquals(featureCollectionResult,featureCollection);
    }

    @Test
    public void save_IsCorrect() {

        //Data for SoundAndLocation Object
        double latitude = 10.0;
        double longitude = 20.0;
        double magnitude = 30.0;

        final SoundAndLocation soundAndLocation = new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now());

        assertThat(soundAndLocation).isNotNull();
    }

    @Test
    public void deleteById_TryInvokeTwoTimesForOneObject() {

        final Long id = 1L;

        soundAndLocationManager.deleteById(id);
        soundAndLocationManager.deleteById(id);

        verify(soundAndLocationRepo,times(2)).deleteById(id);
    }

    @Test
    public void getLocation() {
    }

    @Test
    public void getPointsBetweenDate_WhenNotNullValuesGivenAndOrdered() {

        //Data for repository function
        final LocalDateTime firstDate = LocalDateTime.of(2015, Month.JUNE, 29, 10, 30, 40);
        final LocalDateTime secondDate = LocalDateTime.of(2015, Month.AUGUST, 12, 14, 21, 12);

        //Data to create SoundAndLocation Objects
        double latitude = 10.0;
        double longitude = 20.0;
        double magnitude = 30.0;

        List<SoundAndLocation> soundAndLocationList = new ArrayList<>();

        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));
        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));
        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));
        soundAndLocationList.add(new SoundAndLocation(latitude,longitude,magnitude,LocalDateTime.now()));

        OffsetDateTime odt = OffsetDateTime.now ();
        ZoneOffset zoneOffset = odt.getOffset ();

        Instant instant1 = firstDate.toInstant(zoneOffset);
        Instant instant2 = secondDate.toInstant(zoneOffset);

        long firstDateMil = instant1.toEpochMilli();
        long secondDateMil = instant2.toEpochMilli();

        FeatureCollection featureCollection = convertToFeatureCollection.convertToFeatureCollection(soundAndLocationList);

        //given
        given(soundAndLocationRepo.findAllByDateTimeBetween(firstDate,secondDate)).willReturn(soundAndLocationList);
        //when
        FeatureCollection featureCollectionResult = soundAndLocationManager.getPointsBetweenDate(firstDateMil,secondDateMil);
        //then
        assertEquals(featureCollectionResult,featureCollection);
    }
}