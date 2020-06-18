package com.luckyun.tjam.sso.ssologin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TempleConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    }
