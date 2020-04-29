package pl.pk.project.pz.sound_intensity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import pl.pk.project.pz.sound_intensity.Oauth.CustomUserDetailsService;
import pl.pk.project.pz.sound_intensity.Oauth.Filter.JWTAuthenticationFilter;
import pl.pk.project.pz.sound_intensity.Oauth.Filter.JWTAuthorizationFilter;
import pl.pk.project.pz.sound_intensity.dao.UserRepo;

@EnableAuthorizationServer
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/location").authenticated()
                .antMatchers(HttpMethod.DELETE, "api/location").hasRole("ADMIN")
                .antMatchers("/about").authenticated()
                .anyRequest().permitAll().and().formLogin()
                .and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
        /*SecurityConfigurer securityConfigurerAdapter = new AuthTokenConfig(customUserDetailsService);
        http.apply(securityConfigurerAdapter);*/
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(PasswordEncoder());
    }
}
