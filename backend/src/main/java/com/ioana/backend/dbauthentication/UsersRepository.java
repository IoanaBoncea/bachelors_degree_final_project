package com.ioana.backend.dbauthentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, CrudRepository<Users, Long> {
    public Users findByEmail(String email);

    boolean existsByEmail(String email);
    public Users findByRole(int role);
}
