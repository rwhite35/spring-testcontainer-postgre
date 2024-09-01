package com.demo.greetings.domain.internal;

import java.util.Optional;

// import org.hibernate.validator.internal.util.logging.LoggerFactory;
// import org.slf4j.Logger;// 

import com.demo.greetings.clients.greeting.GreetingServiceClient;
import com.demo.greetings.domain.GreetingService;
import com.demo.greetings.domain.models.CreateGreetingRequest;
import com.demo.greetings.domain.models.Greeting;

public class DefaultGreetingService implements GreetingService {

    // private static final Logger log =
    // LoggerFactory.getLogger(DefaultGreetingService.class);

    private final GreetingRepository greetingRepository;
    private final GreetingServiceClient greetingServiceClient;

    public DefaultGreetingService(
            GreetingRepository greetingRepository,
            GreetingServiceClient greetingServiceClient) {
        this.greetingRepository = greetingRepository;
        this.greetingServiceClient = greetingServiceClient;
    }

    @Override
    public void createGreeting(CreateGreetingRequest request) {
        GreetingEntity entity = new GreetingEntity();
        entity.setUui(request.uui());
        entity.setUsername(request.username());

        greetingRepository.save(entity);
    }

    @Override
    public Optional<Greeting> getGreetingByName(String username) {
        if (this.isGreetingAvailable(username)) {
            System.out.println("Greeting is available.");
        } else {
            return Optional.empty();
        }

        Optional<GreetingEntity> greetingEntity = greetingRepository.findByUsername(username);
        if (greetingEntity.isEmpty()) {
            return Optional.empty();
        }
        return greetingEntity.map(this::toGreeting);
    }

    private boolean isGreetingAvailable(String username) {
        try {
            return greetingServiceClient.getUsername(username).equals(username);
        } catch (Exception e) {
            System.err.println("Error while calling inventory service");
            return true;
        }
    }

    private Greeting toGreeting(GreetingEntity entity) {
        return new Greeting(entity.getId(), entity.getUui(), entity.getUsername());
    }
}
