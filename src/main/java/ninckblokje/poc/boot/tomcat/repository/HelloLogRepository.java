package ninckblokje.poc.boot.tomcat.repository;

import ninckblokje.poc.boot.tomcat.entity.HelloLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloLogRepository extends JpaRepository<HelloLog, Long> {
}
