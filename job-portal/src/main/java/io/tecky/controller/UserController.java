package io.tecky.controller;

import io.tecky.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

//https://stackoverflow.com/questions/72719400/how-to-configure-oauth2-in-spring-boot-be-spring-boot-fe-keycloak
//https://juejin.cn/post/7096722338086912031

@Controller
public class UserController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor =
                new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(
                            HttpRequest request, byte[] bytes,
                            ClientHttpRequestExecution execution) throws IOException {
                        request.getHeaders().add("Authorization", "Bearer " + accessToken);
                        return execution.execute(request, bytes);
                    }
                };

        return interceptor;
    }

    @RequestMapping("/")
    public String newHello(OAuth2AuthenticationToken authentication, Model model){
        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(authorizedClient.getAccessToken().getTokenValue()));
        User response = restTemplate.getForObject("http://localhost:8081/user/info",User.class);
        model.addAttribute("username",response.getUsername());
        model.addAttribute("sid",response.getId());
        return "index";
    }
}

