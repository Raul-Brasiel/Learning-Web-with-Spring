package br.edu.iftm.springapp.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import br.edu.iftm.springapp.service.FoodService;

@TestConfiguration
public class TestConfig {
    @Bean
    public FoodService foodService(){
        return Mockito.mock(FoodService.class);
    }
}
