package br.com.api.test.junit_mockito.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.repositories.UserRepository;

@Service
public class StartDBService {
    
    @Autowired
    private UserRepository userRepository;

    public void instatiateDataBase() {
        User user1 = new User(null, "Donatello", "donatello@gmail.com", "12345");
        User user2 = new User(null, "Michelangelo", "michelangelo@gmail.com", "12345");

        this.userRepository.saveAll(Arrays.asList(user1, user2));
    }

}
