package com.ioana.backend.dbauthentication;

import java.util.List;

public interface UserService {
    List<Users> findAllUsers();
    Users finById(long id);
    Users insert(Users user);
    boolean delete(long id);
    boolean update(Users user);
}
