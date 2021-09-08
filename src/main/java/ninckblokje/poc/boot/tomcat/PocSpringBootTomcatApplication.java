package ninckblokje.poc.boot.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class PocSpringBootTomcatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocSpringBootTomcatApplication.class, args);
    }

}
