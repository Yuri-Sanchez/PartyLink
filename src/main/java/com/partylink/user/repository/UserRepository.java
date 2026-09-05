package com.partylink.user.repository;

import com.partylink.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id")
    Optional<User> findByIdWithRoles(@Param("id") UUID id);
}
