package br.com.api.test.junit_mockito.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.api.test.junit_mockito.services.StartDBService;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private StartDBService startDBService;

    @Bean
    public void instatiateDataBase() {
        this.startDBService.instatiateDataBase();
    }
    
}
