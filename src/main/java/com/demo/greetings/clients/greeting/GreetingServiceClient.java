package com.demo.greetings.clients.greeting;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface GreetingServiceClient {

    @GetExchange("/api/greeting/{username}")
    GreetingName getUsername(@PathVariable String username);
}
