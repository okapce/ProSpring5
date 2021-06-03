package com.prospring.ch4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.prospring.ch4.FoodProviderService;
import com.prospring.ch4.kinder.FoodProviderServiceImpl;

@Configuration
@Profile("kindergarten")
public class KindergartenConfig {
 @Bean
 public FoodProviderService foodProviderService(){
 return new FoodProviderServiceImpl();
 }
}
