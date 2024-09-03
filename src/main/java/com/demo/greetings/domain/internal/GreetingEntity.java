package com.demo.greetings.domain.internal;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "greetings")
public class GreetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(nullable = false, unique = true)
    private String username;

    // singleton initializer
    public GreetingEntity() {
        System.out.println("GreetingEntity initialized!");
    }

    // object constructor
    public GreetingEntity(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    // getter/settings
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        System.out.println("GreetingEntity setting username with" + name);
        this.username = name;
    }
}
