package com.demo.greetings.api;

// load these first so they are available on request
import com.demo.greetings.domain.models.Greeting;
import com.demo.greetings.clients.greeting.GreetingsServiceClient;
import com.demo.greetings.domain.GreetingService;
import com.demo.greetings.domain.internal.DefaultGreetingService;
import com.demo.greetings.domain.internal.Greeted;
import com.demo.greetings.domain.internal.GreetedRepository;
import com.demo.greetings.domain.models.CreateGreetingRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Controller for request to /api/greetings
//
@RestController
@RequestMapping("/api/greetings")
class GreetingController implements GreetingService {

    private final GreetedRepository GreetedRepository;
    // private final GreetingsServiceClient greetingServiceClient;

    /*
     * private final GreetingService greetingService;
     * GreetingController(GreetingService greetingService) {
     * this.greetingService = greetingService;
     * }
     */
    GreetingController(GreetedRepository GreetedRepository) {
        this.GreetedRepository = GreetedRepository;
        // this.greetingServiceClient = greetingServiceClient;
    }

    // GET request wrapper method
    // parsed /api/greetings/{username}, but not /{username}
    // ex: curl -X "GET" 'http://localhost:8080/api/greetings/Victor'
    //
    @GetMapping("/{username}")
    ResponseEntity<Greeting> readGreeting(@PathVariable String username) {
        System.out.println("GC query username:" + username);

        Optional<Greeting> result = responseGreeting(username);
        if (result.isEmpty()) {
            System.out.println("GC didnt find name " + username + " in db.");
            return ResponseEntity.ok(new Greeting(null, username));

        } else {
            System.out.println("GC found name " + username + " in db!");
            return ResponseEntity.ok(new Greeting(result.get().id(), username));
        }
    }

    // POST request wrapper method
    // handles /api/greeting : HTTP content-type application/json
    // ex: curl -v -X "POST" 'http://localhost:8080/api/greetings \'
    // --header 'Content-Type: application/json' \
    // --data '{"username":"Joe"}'
    //
    @PostMapping
    public void createGreeting(@Validated @RequestBody CreateGreetingRequest request) {
        System.out.println("GC received new greeting request:" + request);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/greetings/{username}")
                .buildAndExpand(request.username())
                .toUri();
        System.out.println("GC returning response entity for exchange as uri: " + uri);

        ResponseEntity.created(uri).build();

    }

    @Override
    public Optional<Greeting> responseGreeting(String username) {

        List<Greeted> greetlist = GreetedRepository.findByUsername(username);
        if (greetlist.get(0).getUsername().isEmpty()) {
            System.out.println("DGS Received name is not in repository!");
            return Optional.of(new Greeting(null, null));

        } else {
            System.out.println("DGS Received name is in repository! With record id " + greetlist.toString());
            return Optional.of(new Greeting(greetlist.get(0).getId(), username));
        }
    }
}
