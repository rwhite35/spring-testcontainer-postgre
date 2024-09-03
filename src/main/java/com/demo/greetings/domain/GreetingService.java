package com.demo.greetings.domain;

import com.demo.greetings.domain.models.CreateGreetingRequest;
import com.demo.greetings.domain.models.Greeting;
import java.util.Optional;
// import java.util.UUID;

public interface GreetingService {

    // record expects non name as form input,
    // insert auto increments id, auto generates uuid
    void createGreeting(CreateGreetingRequest request);

    // returns greeting.username, greeting.uuid if found
    Optional<Greeting> readGreeting(String username);

    // stubbed for later
    // boolean updateGreeting(String name);
    // boolean deleteGreeting(String name);
}
