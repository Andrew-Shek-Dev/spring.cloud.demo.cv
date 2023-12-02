package io.tecky.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Java 8 later, lambda expression
        //https://stackoverflow.com/questions/22703412/java-lambda-expressions-not-supported-at-this-language-level
//        http.authorizeHttpRequests(
//                req->req.anyRequest().permitAll()
//        ).csrf().disable()
//        .headers().frameOptions().sameOrigin();
        //Java 8 below, no lambda expression
//        http.authorizeHttpRequests()
//                .anyRequest()
//                .permitAll()
//                .and().csrf().disable()
//                //https://stackoverflow.com/questions/26220083/h2-database-console-spring-boot-load-denied-by-x-frame-options
//                .headers().frameOptions().sameOrigin();


//        http.authorizeHttpRequests(
//                //antMatchers, mvcMatchers will be deprecated at Spring Boot 6 and replace by requestMatchers
//                //https://stackoverflow.com/questions/74753700/cannot-resolve-method-antmatchers-in-authorizationmanagerrequestmatcherregis
//                //https://docs.spring.io/spring-security/reference/whats-new.html
//                    req->req.requestMatchers("/api/**").authenticated()
//                            .anyRequest().permitAll()
//                )
//                .formLogin(login->login.loginPage("/login"))
//                .logout(logout->logout.permitAll())
//                .csrf()
//                .disable()
//                .headers().frameOptions().sameOrigin();

        http.csrf().disable().headers().frameOptions().sameOrigin();
        http.authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and().formLogin();

        return http.build();
    }
}
