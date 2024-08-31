package com.demo.greetings.domain.internal;

import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "greetings")
public class GreetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "UUID must not be empty")
    private UUID uui;

    @Nonnull
    @Column(nullable = false, unique = true)
    private String username;

    // singleton initializer
    public GreetingEntity() {
    }

    // object constructor
    public GreetingEntity(Long id, UUID uui, String username) {
        this.id = id;
        this.uui = uui;
        this.username = username;
    }

    // getter/settings
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uui;
    }

    public void setUuid(UUID uui) {
        this.uui = uui;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
}
