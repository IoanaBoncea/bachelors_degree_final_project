package com.ioana.backend.dbauthentication;

import com.ioana.backend.assign.Assignment;
import com.ioana.backend.entity.ProfessorsCodes;
import com.ioana.backend.task.AssignationStudentTask;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_table")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "user", fetch =  FetchType.LAZY)
    Set<Assignment> assignments = new HashSet<>();

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private int role;

    @Column(name="year")
    private int year;

    @Column(name = "domain")
    private String domain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="code_id")
    private ProfessorsCodes professorsCodes;


    public Users(String firstName, String lastName, String email, String password, int role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public Users(String firstName, String lastName, String email, String encodedPassword, int role, String domain, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = encodedPassword;
        this.role = role;
        this.domain = domain;
        this.year= year;

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Users() {
    }

    public Users(Set<Assignment> assignments, String firstName, String lastName, String email, String password, int role, int year, String domain, Set<AssignationStudentTask> assignation) {
        this.assignments = assignments;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.year = year;
        this.domain = domain;
        this.assignation = assignation;
    }

    @OneToMany(mappedBy = "user",
    fetch = FetchType.LAZY,
    cascade = CascadeType.REMOVE)
    Set<AssignationStudentTask> assignation = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
