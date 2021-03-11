package by.nintendo.datajpa;

import by.nintendo.datajpa.model.Role;
import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.service.UserService;
import by.nintendo.datajpa.storage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

}
