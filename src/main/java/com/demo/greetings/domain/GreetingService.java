package com.demo.greetings.domain;

import java.util.Optional;
import java.util.UUID;

import com.demo.greetings.domain.models.*;

public interface GreetingService {

    // creates a new record with username and uuid
    void createGreeting(CreateGreetingRequest request);

    // returns greeting.username, greeting.uuid if found
    Optional<Greeting> getGreetingByName(String username);

    // see GreetingRepository.
    void updateGreetingUuid(String username, UUID uuid);
}
