package com.demo.greetings.domain.internal;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

// Table name should match class name if planning to use JpaRespository API.
// specially if using NamedQuery or Query annotation

// @NamedQuery(name = "Greeted.findByUsername", query = "select g from Greeted g
// where g.username = ?1")
@Entity
@Table(name = "GREETED")
public class Greeted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    @Column(nullable = false, unique = true)
    private String username;

    // constructor doesn't accept parameters, must
    // use setter before passing this entity to a consumer
    public Greeted() {
        System.out.println("Greeted initialized!");
    }

    // object constructor
    public Greeted(Long id, String username) {
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
        System.out.println("Greeted setting username with" + name);
        this.username = name;
    }
}
