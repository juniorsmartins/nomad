package com.nomad.accounting_analysis.config.bean;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.setConnectTimeout(Duration.ofMillis(300000))
//                .setReadTimeout(Duration.ofMillis(300000))
//                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(300000); // 300 segundos
        requestFactory.setReadTimeout(300000); // 300 segundos

        return new RestTemplate(requestFactory);
    }
}

