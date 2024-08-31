package com.demo.greetings.domain.internal;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GreetingRepository {
    Optional<GreetingEntity> findByName(String username);

    @Modifying
    @Query("update GreetingsEntity g set g.uui =:uui where g.username =:username")
    void updateGreetingUuid(@Param("username") String username, @Param("uui") UUID uui);

}
