package pl.pk.project.pz.sound_intensity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
public class SoundIntensityApplication{

    public static void main(String[] args) {
        SpringApplication.run(SoundIntensityApplication.class, args);
    }

}
