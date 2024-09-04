package com.demo.greetings.domain.internal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GreetedRepository extends JpaRepository<Greeted, Long> {

    // selects matching user name and return all fields if found
    @Query("select u from Greeted u where u.username=?1")
    List<Greeted> findByUsername(@Param("username") String username);
}
