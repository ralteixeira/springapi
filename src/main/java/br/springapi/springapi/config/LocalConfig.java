package br.springapi.springapi.config;

import br.springapi.springapi.domain.User;
import br.springapi.springapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDb(){

        User u1 = new User(null, "Joao", "joao@email.com", "1234");
        User u2 = new User(null, "Joana", "joana@email.com", "1234");

        repository.saveAll(List.of(u1,u2));
    }

}
