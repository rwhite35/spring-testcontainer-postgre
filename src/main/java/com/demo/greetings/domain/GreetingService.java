package com.demo.greetings.domain;

import com.demo.greetings.domain.models.CreateGreetingRequest;
import com.demo.greetings.domain.models.Greeting;
import java.util.Optional;
// import java.util.UUID;

public interface GreetingService {

    // creates a new record with username and uuid
    void createGreeting(CreateGreetingRequest request);

    // returns greeting.username, greeting.uuid if found
    Optional<Greeting> getGreetingByName(String username);

    // see GreetingRepository.
    // void updateGreetingUuid(UUID uui, String username);
}
