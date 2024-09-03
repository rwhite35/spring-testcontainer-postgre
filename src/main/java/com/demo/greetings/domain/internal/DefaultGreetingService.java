package com.demo.greetings.domain.internal;

import java.util.Optional;

import com.demo.greetings.clients.greeting.GreetingName;
import com.demo.greetings.clients.greeting.GreetingServiceClient;
import com.demo.greetings.domain.GreetingService;
import com.demo.greetings.domain.models.CreateGreetingRequest;
import com.demo.greetings.domain.models.Greeting;

public class DefaultGreetingService implements GreetingService {

    private final GreetingRepository greetingRepository;
    private final GreetingServiceClient greetingServiceClient;

    public DefaultGreetingService(
            GreetingRepository greetingRepository,
            GreetingServiceClient greetingServiceClient) {
        this.greetingRepository = greetingRepository;
        this.greetingServiceClient = greetingServiceClient;
    }

    // wrapper method for saving username input
    // listens for route domain/api/greetings/{username}
    //
    @Override
    public void createGreeting(CreateGreetingRequest request) {
        System.out.println("DGS received create new greeting request!");

        GreetingEntity entity = new GreetingEntity();
        entity.setUsername(request.username());

        greetingRepository.save(entity);
    }

    // wrapper method for reading name from saved source
    // listens for route domain/{username}
    //
    @Override
    public Optional<Greeting> readGreeting(String username) {
        if (this.isNameReceived(username)) {
            System.out.println("DGS Name was received!");
        } else {
            return Optional.empty();
        }

        // next must validate before saving
        Optional<GreetingEntity> greetingEntity = greetingRepository.findByUsername(username);
        if (greetingEntity.isEmpty()) {
            System.out.println("DGS Received name is not in repository!");
        }
        return greetingEntity.map(this::toGreeting);
    }

    // determines if client parsed name parameter from URI
    //
    private boolean isNameReceived(String username) {
        try {
            GreetingName receivedName = greetingServiceClient.getUsername(username);
            return receivedName.username().isEmpty();

        } catch (Exception e) {
            System.err.println("DGS Error while calling inventory service");
            return true;
        }
    }

    // converts entity to greeting record
    //
    private Greeting toGreeting(GreetingEntity entity) {
        return new Greeting(entity.getId(), entity.getUsername());
    }
}
