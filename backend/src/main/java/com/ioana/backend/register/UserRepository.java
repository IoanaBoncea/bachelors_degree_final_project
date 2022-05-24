package com.ioana.backend.register;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByRole(int role);
    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
}
