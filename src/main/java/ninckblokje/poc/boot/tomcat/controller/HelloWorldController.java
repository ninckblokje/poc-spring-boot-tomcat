package ninckblokje.poc.boot.tomcat.controller;

import ninckblokje.poc.boot.tomcat.entity.HelloLog;
import ninckblokje.poc.boot.tomcat.repository.HelloLogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/hello")
public class HelloWorldController {

    private final HelloLogRepository repository;

    public HelloWorldController(HelloLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String helloWorld(Principal principal) {
        repository.save(HelloLog.builder().dateTime(LocalDateTime.now()).user(principal.getName()).build());
        return String.format("Hello %s!", principal.getName());
    }

    @GetMapping("/log")
    public List<HelloLog> getLog() {
        return repository.findAll();
    }

    @GetMapping("/log/count")
    public long countLog() {
        return repository.count();
    }
}
