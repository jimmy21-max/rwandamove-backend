package rw.rwandamove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "rw.rwandamove")
public class RwandamoveBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RwandamoveBackendApplication.class, args);
    }

}