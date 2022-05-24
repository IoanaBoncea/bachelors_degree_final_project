package com.ioana.backend.dbauthentication;

import com.ioana.backend.dbauthentication.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, CrudRepository<Users, Long> {
    public Users findByEmail(String email);
}
