package com.demo.greetings;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

// API Configuration: required for RestClientConfig and ServletWebServer context
// uses @Bean RestClientConfig to autowire the endpoint to its service.

@ConfigurationProperties(prefix = "application")
@Validated
public record ApplicationProperties(@NotEmpty String greetingsServiceUrl) {
}
