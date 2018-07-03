package pl.arimr.datafilteringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.arimr.datafilteringdemo.repositories.base.BaseRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class DataFilteringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataFilteringDemoApplication.class, args);
    }
}
