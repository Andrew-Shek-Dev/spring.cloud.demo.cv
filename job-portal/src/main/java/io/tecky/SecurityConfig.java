package io.tecky;


import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
//https://howtodoinjava.com/spring-boot/oauth2-login-with-keycloak-and-spring-security/

@KeycloakConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(server->server.configurationSource(corsConfigurationSource()));
        http.oauth2Login(Customizer.withDefaults());
        http.formLogin(form->form.loginPage("/login")); //<-if "/", redirect to Keycloak Admin Portal automatically
        // @formatter:off
        http.authorizeHttpRequests(req->
                req.requestMatchers("/user/info","/actuator/health/readiness", "/actuator/health/liveness", "/v3/api-docs/**","/login" /*if removed, redirect to Keycloak Admin Portal automatically*/).permitAll()
                        .anyRequest().authenticated());
        // @formatter:on
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        //For test purpose, please set the whitelist in production case
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));

        // Limited to API routes (neither actuator nor Swagger-UI)
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
