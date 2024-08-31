package com.demo.greetings.domain.internal;

import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import jakarta.validation.constraints.NotNull.message
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "greetings")
public class GreetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "UUID must not be empty")
    private UUID uuid;

    @Nonnull
    @Column(nullable = false, unique = true)
    private String username;

    // singleton initializer
    public GreetingEntity() {
    }

    // object constructor
    public GreetingEntity(Long id, UUID uuid, String username) {
        this.id = id;
        this.uuid = uuid;
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
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
}
