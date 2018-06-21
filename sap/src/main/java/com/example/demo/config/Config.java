package com.example.demo.config;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.service.ProductStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public ProductDao productDao() {
        return new ProductDao();
    }

    @Bean
    public CategoryDao categoryDao() { return new CategoryDao();
    }

    @Bean
    public ProductStorageService productStorageService() {return  new ProductStorageService();}

    @Bean
    RestTemplate restTemplate(){
       return new RestTemplate();
    }

}

