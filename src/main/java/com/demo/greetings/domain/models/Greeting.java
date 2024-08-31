package com.demo.greetings.domain.models;

import java.util.UUID;

public record Greeting(Long id, String username, UUID uui) {
}
