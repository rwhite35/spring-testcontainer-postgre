package com.demo.greetings.api;

// load these first so they are available on request
import com.demo.greetings.domain.GreetingService;
import com.demo.greetings.domain.NameDoesntExistException;
// import com.demo.greetings.domain.internal.DefaultGreetingService;
import com.demo.greetings.domain.models.Greeting;
import com.demo.greetings.domain.models.CreateGreetingRequest;

import java.net.URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
// import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// optionally accepts one parameter {username}
// where greetings is db table name
//
// @Import(DefaultGreetingService.class)
@RestController
@RequestMapping("/api/greetings")
class GreetingController {

    /*
     * private final GreetingService greetingService;
     * GreetingController(GreetingService greetingService) {
     * this.greetingService = greetingService;
     * }
     */
    GreetingController() {
    }

    @PostMapping
    ResponseEntity<Void> createGreeting(@Validated @RequestBody CreateGreetingRequest request) {
        System.out.println("GC received new greeting request:" + request);

        // greetingService.createGreeting(request);

        // current context path defined in RestClientConfig bean which
        // gets the path from Application.Properties.greetingServiceUrl

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/greetings/{username}")
                .buildAndExpand(request.username())
                .toUri();

        System.out.println("GC returning response entity for exchange as uri: " + uri);
        return ResponseEntity.created(uri).build();
    }

    // GET Method
    @GetMapping("/{username}")
    ResponseEntity<Greeting> readGreeting(@PathVariable String username) {
        System.out.println("GC received read username:" + username);
        /*
         * var greeting = greetingService.readGreeting(username)
         * .orElseThrow(() -> NameDoesntExistException.withName(username));
         */
        var greeting = new Greeting(null, username);

        // returns greeting.username, greeting.uui
        // return ResponseEntity.ok(greeting);
        System.out.println("GC returns response entity for exchange as Greeting.username:" + username);
        return ResponseEntity.ok(greeting);
    }

}
