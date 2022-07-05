package com.ioana.backend.dbauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public String processRegister(@RequestBody SignUpRequest signUpRequest) {
        if (usersRepository.existsByEmail(signUpRequest.getEmail())) {
            return "Error: A user with that email already exists!";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Users newUser = new Users(signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                encodedPassword,
                signUpRequest.getRole(),
                signUpRequest.getDomain(),
                signUpRequest.getYear());

        userDetailsService.insert(newUser);

        return "register_success";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginProcess(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        Users user = (Users) authentication.getPrincipal();
        UserDetailsImpl userDetails = new UserDetailsImpl(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole(), user.getYear(), user.getDomain());

        return ResponseEntity.ok(new LoginResponse(jwtToken,
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                userDetails.getRole(),
                userDetails.getYear(),
                userDetails.getDomain()));
    }

    @GetMapping("")
    public List<Users> getAllUsers() {
        return userDetailsService.findAllUsers();
    }

    @GetMapping("{id}")
    public Users getPerson(@PathVariable long id) {
        return userDetailsService.findById(id);
    }

    @PostMapping("")
    public String addPerson(@RequestBody Users user) {

        if (user != null) {
            userDetailsService.insert(user);
            return "Added a user";
        } else {
            return "Request does not contain a body";
        }
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") long id) {

        if (id > 0) {
            if (userDetailsService.delete(id)) {
                return "Deleted the user.";
            } else {
                return "Cannot delete the user.";
            }
        }
        return "The id is invalid for the user.";
    }

    @PutMapping("")
    public String updatePerson(@RequestBody Users user) {
        if (user != null) {
            userDetailsService.update(user);
            return "Updated users.";
        } else {
            return "Request does not contain a body";
        }
    }

    @GetMapping("/students")
    public List<Users> usersWithStudentRole() {
        return usersRepository.findAll()
                .stream()
                .filter(users -> users.getRole() == 1)
                .collect(Collectors.toList());
    }

}
