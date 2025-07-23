package com.example.warehouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean
    public RestClient shopRestClient(@Value("${app.integration.shop-base-url}") String shopBaseUrl) {
        return RestClient.create(shopBaseUrl);
    }

}
