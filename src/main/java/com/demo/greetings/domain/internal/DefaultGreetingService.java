package com.demo.greetings.domain.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.greetings.clients.greeting.GreetingName;
import com.demo.greetings.clients.greeting.GreetingsServiceClient;
import com.demo.greetings.domain.GreetingService;
import com.demo.greetings.domain.models.CreateGreetingRequest;
import com.demo.greetings.domain.models.Greeting;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DefaultGreetingService implements GreetingService {

    private final GreetedRepository greetedRepository;
    private final GreetingsServiceClient greetingServiceClient;

    public DefaultGreetingService(
            GreetedRepository greetedRepository,
            GreetingsServiceClient greetingServiceClient) {
        this.greetedRepository = greetedRepository;
        this.greetingServiceClient = greetingServiceClient;
    }

    // wrapper method for saving username input
    // listens for route domain/api/greetings/{username}
    //
    @Override
    public void createGreeting(CreateGreetingRequest request) {
        System.out.println("DGS received new greetings request " + request);
        Greeted entity = new Greeted();
        entity.setUsername(request.username());
        greetedRepository.save(entity);
    }

    // wrapper method for reading name from saved source
    // listens for route domain/{username}
    //
    @Override
    public Optional<Greeting> responseGreeting(String username) {
        System.out.println("DGS received new username request " + username);
        if (this.isNameReceived(username)) {
            System.out.println("DGS Name was received!");
        } else {
            return Optional.empty();
        }

        // next must validate before saving
        List<Greeted> greetlist = greetedRepository.findByUsername(username);
        if (greetlist.get(0).getUsername().isEmpty()) {
            System.out.println("DGS name is not in repository!");
            return Optional.of(new Greeting(null, null));

        } else {
            System.out.println("DGS name already created! With record id " + greetlist.toString());
            return Optional.of(new Greeting(greetlist.get(0).getId(), username));
        }
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
}
