package dev.stankiewicz.example.poolservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PoolServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoolServiceApplication.class, args);
    }

}
