package pl.employer.assistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableWebSecurity
@EnableSwagger2
public class AssistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistanceApplication.class, args);
    }
}
