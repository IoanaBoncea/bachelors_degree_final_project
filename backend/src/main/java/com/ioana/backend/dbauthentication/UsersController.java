package com.ioana.backend.dbauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/register")
    public String processRegister(Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        this.userDetailsService.insert(user);

        return "register_success";
    }

    @PostMapping("/login")
    public UserDetails loginProcess(Users user){
        return
    }

    @GetMapping("")
    public List<Users> getAllUsers(){
        return userDetailsService.findAllUsers();
    }

    @GetMapping("{id}")
    public Users getPerson(@PathVariable long id) {
        return userDetailsService.finById(id);
    }

    @PostMapping("")
    public String addPerson(@RequestBody Users user) {

        if(user != null) {
            userDetailsService.insert(user);
            return "Added a user";
        } else {
            return "Request does not contain a body";
        }
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") long id) {

        if(id > 0) {
            if(userDetailsService.delete(id)) {
                return "Deleted the user.";
            } else {
                return "Cannot delete the user.";
            }
        }
        return "The id is invalid for the user.";
    }

    @PutMapping("")
    public String updatePerson(@RequestBody Users user) {
        if(user != null) {
            userDetailsService.update(user);
            return "Updated users.";
        } else {
            return "Request does not contain a body";
        }
    }
}
