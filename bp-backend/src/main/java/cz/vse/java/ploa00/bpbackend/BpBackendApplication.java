package cz.vse.java.ploa00.bpbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BpBackendApplication {

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }

    public static void main(String[] args) {
        SpringApplication.run(BpBackendApplication.class, args);
    }
}
