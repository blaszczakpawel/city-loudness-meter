package pl.pk.project.pz.sound_intensity.Oauth.Filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.pk.project.pz.sound_intensity.Oauth.CustomUserPrincipal;
import pl.pk.project.pz.sound_intensity.dao.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        String username = obtainUsername(request);
        String password = obtainPassword(request);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, username, new ArrayList<>()));

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((CustomUserPrincipal)authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 10000000))
                .signWith(SignatureAlgorithm.HS512,"Tt_RrtBM9ZJsrzRGfqmePSbKfHTHDDueXHtIvTO0K3RRcbGc2fZT3UdfaYqpVa_amNJpebOxOgr42iEaQYK9hg").compact();
        response.addHeader("Authorization","Bearer " + token);

    }
}

