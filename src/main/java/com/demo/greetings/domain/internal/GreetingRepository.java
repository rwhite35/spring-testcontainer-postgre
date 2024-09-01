package com.demo.greetings.domain.internal;

import java.util.Optional;
// import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

public interface GreetingRepository extends JpaRepository<GreetingEntity, Long> {

    Optional<GreetingEntity> findByUsername(String username);

    // @Query("select GreetingsEntity g where g.username =:username")
    // void selectGreetingName(@Param("username") String username);

    // see GreetingService for implementation
    // @Modifying
    // void updateGreetingUuid(@Param("username") String username, @Param("uui")
    // UUID uui);

}
