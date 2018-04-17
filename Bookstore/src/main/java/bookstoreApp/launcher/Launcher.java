package bookstoreApp.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
@SpringBootApplication
*/
@EntityScan(basePackages ={"bookstoreApp.model"})
@SpringBootApplication(scanBasePackages = {"bookstoreApp.launcher", "bookstoreApp.model", "bookstoreApp.repository", "bookstoreApp.service", "bookstoreApp.controller"})
@EnableJpaRepositories(basePackages = {"bookstoreApp.repository"})
public class Launcher  {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}

