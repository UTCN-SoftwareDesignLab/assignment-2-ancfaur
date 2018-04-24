package bookstoreApp.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
@SpringBootApplication
*/
@EntityScan(basePackages ={"bookstoreApp.entity"})
@SpringBootApplication(scanBasePackages = {"bookstoreApp.launcher", "bookstoreApp.entity", "bookstoreApp.dto", "bookstoreApp.repository", "bookstoreApp.service", "bookstoreApp.controller", "bookstoreApp.converter", "bookstoreApp.config"})
@EnableJpaRepositories(basePackages = {"bookstoreApp.repository"})
public class Launcher  {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}

