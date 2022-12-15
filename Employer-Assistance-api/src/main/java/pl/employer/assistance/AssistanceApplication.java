package pl.employer.assistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import pl.employer.assistance.config.JwtFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
//@EnableSwagger2
public class AssistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistanceApplication.class, args);
    }
}
