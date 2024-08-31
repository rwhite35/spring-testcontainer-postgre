package com.demo.greetings.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.greetings.domain.GreetingService;
import com.demo.greetings.domain.NameDoesntExistException;
import com.demo.greetings.domain.models.*;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// optionally accepts one parameter {username}
@RestController
@RequestMapping("/api/greeting")
class GreetingController {
    private final GreetingService greetingService;

    // constructor
    GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    ResponseEntity<Void> createGreeting(@Validated @RequestBody CreateGreetingRequest request) {
        greetingService.createGreeting(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/greeting/{username}")
                .buildAndExpand(request.username())
                .toUri();

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
