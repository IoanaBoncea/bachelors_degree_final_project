package com.ioana.backend.controller;

import com.ioana.backend.exception.ResourceNotFoundException;
import com.ioana.backend.dbauthentication.Users;
import com.ioana.backend.dbauthentication.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TasksController {
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @GetMapping("users")
//    public List<Users> getAllTasks(){
//        return this.usersRepository.findAll();
//    }
//
//    @GetMapping("test")
//    public String test(){
//        return "e bine bine e foarte bine";
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long taskId)
//            throws ResourceNotFoundException {
//        Users users = usersRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("User not found for id :: " + taskId));
//        return ResponseEntity.ok().body(users);
//    }
//
//    @PostMapping("users")
//    public Users createUser(@RequestBody Users users){
//        return this.usersRepository.save(users);
//    }
//
//    //@PutMapping("users/{id}")
//    //public ResponseEntity<Users> updateUsers(@PathVariable(value = "id") Long userId, @Validated @RequestBody Users usersDetails){
//    //}
}
