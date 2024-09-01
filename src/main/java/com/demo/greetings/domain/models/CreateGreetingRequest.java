package com.demo.greetings.domain.models;

import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;

public record CreateGreetingRequest(@NotEmpty UUID uui, @NotEmpty String username) {
}
