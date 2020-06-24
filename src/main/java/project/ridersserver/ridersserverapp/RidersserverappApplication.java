package project.ridersserver.ridersserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RidersserverappApplication {

    public static void main(String[] args) {
        SpringApplication.run(RidersserverappApplication.class, args);
    }

}
