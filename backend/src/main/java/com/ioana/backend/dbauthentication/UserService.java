package com.ioana.backend.dbauthentication;

import java.util.List;

public interface UserService {
    List<Users> findAllUsers();
    Users findById(long id);
    Users findByRole(int role);
    Users insert(Users user);
    boolean delete(long id);
    boolean update(Users user);
}
