package pl.pk.project.pz.sound_intensity.Oauth;


import org.springframework.security.jwt.Jwt;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;


import java.util.Date;


public class AppTokenProvider {
    private static final long EXPIRATION_TIME_SECONDS = 864_000_000; // 10 days
    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";


  /* private static String getNewPerishableToken(String userId) {
        return jjwt.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.nanoTime() + EXPIRATION_TIME_SECONDS))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }*/
}
