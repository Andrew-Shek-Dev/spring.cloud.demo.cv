package io.tecky.demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//http://localhost:8082/h2-console/

@EnableJpaRepositories("io.tecky.demo.persistence.repo")
@EntityScan("io.tecky.demo.persistence.model")
@SpringBootApplication
@Slf4j
public class WebPortal
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebPortal.class,args);
    }
}
