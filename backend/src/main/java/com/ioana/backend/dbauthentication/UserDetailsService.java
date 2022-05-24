package com.ioana.backend.dbauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService, UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }


    @Override
    public List<Users> findAllUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    @Override
    public Users finById(long id) {
        Optional<Users> result = usersRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Users insert(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public boolean delete(long id) {
        try {
            usersRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Users user) {
        try {
            usersRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
