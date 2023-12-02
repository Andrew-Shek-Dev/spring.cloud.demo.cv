package io.tecky.spring.cloud.features.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "env",name = "profile",havingValue = "development")
    public SecurityFilterChain filterChain_dev(HttpSecurity http) throws Exception{
        http.csrf()
                .ignoringRequestMatchers("/encrypt/**")
                .ignoringRequestMatchers("/decrypt/**");
        return http.build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "env",name = "profile",havingValue = "production")
    public SecurityFilterChain filterChain_prod(HttpSecurity http) throws Exception{
        http.csrf()
                .ignoringRequestMatchers("/decrypt/**");
        return http.build();
    }
}
