package com.demo.greetings.api;

// load these first so they are available on request
import com.demo.greetings.domain.GreetingService;
import com.demo.greetings.domain.NameDoesntExistException;
import com.demo.greetings.domain.models.Greeting;
import com.demo.greetings.domain.models.CreateGreetingRequest;

import java.net.URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// optionally accepts one parameter {username}
// where greetings is db table name
//
@RestController
@RequestMapping("/api/greetings")
class GreetingController {

    private final GreetingService greetingService;

    // constructor
    GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    ResponseEntity<Void> createGreeting(@Validated @RequestBody CreateGreetingRequest request) {
        greetingService.createGreeting(request);

        // current context path defined in RestClientConfig bean which
        // gets the path from Application.Properties.greetingServiceUrl

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/greetings/{username}")
                .buildAndExpand(request.username())
                .toUri();

        System.out.println("GreetingController uri: " + uri);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{username}")
    ResponseEntity<Greeting> getGreetingByName(@PathVariable String username) {
        var greeting = greetingService.getGreetingByName(username)
                .orElseThrow(() -> NameDoesntExistException.withName(username));

        // returns greeting.username, greeting.uui
        return ResponseEntity.ok(greeting);
    }

}
