package io.tecky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

/*
https://blog.csdn.net/winnershili/article/details/80420201
https://blog.csdn.net/qq_27840695/article/details/81127758
* */
@SpringBootApplication
public class JobPortalServer {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    public static void main(String[] args){
        SpringApplication.run(JobPortalServer.class,args);
    }
}
