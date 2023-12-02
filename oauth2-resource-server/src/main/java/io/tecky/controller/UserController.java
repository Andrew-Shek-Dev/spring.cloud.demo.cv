/**
 https://dzone.com/articles/spring-oauth2-resource-servers
 https://github.com/ch4mpy/spring-addons/tree/6.0.x/samples/tutorials/resource-server_with_jwtauthenticationtoken
 https://stackoverflow.com/questions/74901986/how-to-permitall-anonymous-access-in-spring-boot-3-or-above
 https://stackoverflow.com/questions/74447778/spring-security-in-spring-boot-3
 https://stackoverflow.com/questions/53795179/keycloak-missing-form-parameter-grant-type
 https://www.ankursheel.com/blog/automatically-set-access-token-authenticated-requests-insomnia
 https://www.google.com/search?q=Resourse+Server+cannot+login+keycloak+auth+server&oq=Resourse+Server+cannot+login+keycloak+auth+server&aqs=chrome..69i57.54363j0j1&sourceid=chrome&ie=UTF-8
 */
package io.tecky.controller;

import java.util.stream.Collectors;

import io.tecky.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    //Testing Purpose
    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    public String getGreeting(JwtAuthenticationToken auth) {
        return "Hi %s! You are granted with: %s.".formatted(
                auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ", "[", "]")));
    }

    //認實Mode
    @GetMapping("/info")
    public User getUserInfo(JwtAuthenticationToken auth){
        final String username = auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME);
        final String sid = auth.getToken().getId();
        User test = User.builder().id(sid).username(username).build();
        return test;
    }
}
