package com.demo.greetings.clients.greeting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.greetings.ApplicationProperties;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    GreetingServiceClient greetingServiceProxy(ApplicationProperties properties) {
        RestClient restClient = RestClient.create(properties.greetingServiceUrl());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(GreetingServiceClient.class);
    }
}
