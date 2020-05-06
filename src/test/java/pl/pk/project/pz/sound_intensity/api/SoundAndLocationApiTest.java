package pl.pk.project.pz.sound_intensity.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.pk.project.pz.sound_intensity.manager.SoundAndLocationManager;
import pl.pk.project.pz.sound_intensity.pojo.FeatureCollection;
import pl.pk.project.pz.sound_intensity.pojo.Features;
import pl.pk.project.pz.sound_intensity.pojo.Geometry;
import pl.pk.project.pz.sound_intensity.pojo.Properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;
import pl.pk.project.pz.sound_intensity.pojo.convert.convertToFeatureCollection;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class SoundAndLocationApiTest {


    @Mock
    SoundAndLocationManager soundAndLocationManager;

    @InjectMocks
    SoundAndLocationApi soundAndLocationApi;

    @BeforeAll
    public void setUp() {
        System.out.println("SoundAndLocationApiTest started");
    }

    @AfterAll
    public void tearDown() {
        System.out.println("SoundAndLocationApiTest finished");
    }

    @Test
    public void getAll_CorrectFromAndToDate_CheckServerResponse() {

        List<Features> featuresList = new ArrayList<>();
        featuresList.add(new Features(new Properties(5.2),new Geometry(new Double[]{1.0,2.0})));
        featuresList.add(new Features(new Properties(3.4),new Geometry(new Double[]{2.4,6.4})));

        FeatureCollection featureCollection = new FeatureCollection(featuresList);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        //given
        given(soundAndLocationManager.findAll()).willReturn(featureCollection);
        //when
        ResponseEntity<FeatureCollection> responseEntity;
        try {
            responseEntity = soundAndLocationApi.getAll(12898L,71237L);
            //then
            assertEquals(responseEntity.getStatusCodeValue(), 200);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getAll_CorrectFromAndToDate_CheckReturnType() {

        List<Features> featuresList = new ArrayList<>();
        featuresList.add(new Features(new Properties(5.2),new Geometry(new Double[]{1.0,2.0})));
        featuresList.add(new Features(new Properties(3.4),new Geometry(new Double[]{2.4,6.4})));

        FeatureCollection featureCollection = new FeatureCollection(featuresList);

        ResponseEntity<FeatureCollection> expectedResult = new ResponseEntity<>(featureCollection,HttpStatus.OK);

        //given
        given(soundAndLocationManager.findAll()).willReturn(featureCollection);
        //when
        ResponseEntity<FeatureCollection> responseEntity;
        try {
            responseEntity = soundAndLocationApi.getAll(12898L,71237L);
            //then
            assertEquals(responseEntity,expectedResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getById() {
    }

    @Test
    public void addNewPoint() {
    }

    @Test
    public void deletePoint() {
    }

    @Test
    public void getLocation() {
    }

    @Test
    public void getTrue() {
    }
}