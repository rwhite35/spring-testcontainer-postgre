package com.demo.greetings.domain.models;

import jakarta.validation.constraints.NotEmpty;

public record CreateGreetingRequest(@NotEmpty String username) {
}
