package com.demo.greetings.domain.models;

import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;

public record CreateGreetingRequest(@NotEmpty String username, @NotEmpty UUID uui) {
}
