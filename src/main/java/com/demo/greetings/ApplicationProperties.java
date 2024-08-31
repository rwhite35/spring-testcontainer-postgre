package com.demo.greetings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "application")
@Validated
public record ApplicationProperties(
        @NotEmpty String greetingImagesBucketName,
        @NotEmpty String greetingImageUpdateTopic,
        @NotEmpty String greetingServiceUrl) {
}
