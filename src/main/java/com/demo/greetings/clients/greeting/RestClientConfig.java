package com.demo.greetings.clients.greeting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.greetings.ApplicationProperties;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    // theres only one property defined and its 'greetingServiceUrl'
    // see Application and ApplicationProperties.
    //
    // ServletWebServer uses this context to autowire endpoints to services.
    // this is the Brean dependency for GreetingController, and will throw
    // an unsatified dependency error if not configure correctly.

    @Bean
    GreetingServiceClient greetingServiceProxy(ApplicationProperties properties) {
        RestClient restClient = RestClient.create(properties.greetingServiceUrl());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build();

        return factory.createClient(GreetingServiceClient.class);
    }
}
