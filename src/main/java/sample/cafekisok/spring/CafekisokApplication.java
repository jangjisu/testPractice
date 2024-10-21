package sample.cafekisok.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CafekisokApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafekisokApplication.class, args);
    }

}
