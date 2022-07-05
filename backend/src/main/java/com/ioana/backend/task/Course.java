package com.ioana.backend.task;

import com.ioana.backend.assign.Assignment;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    Set<Assignment> UserAssignments = new HashSet<>();

    @Column(name = "id_professor")
    private long id_professor;

    @Column(name = "code_course")
    private String code_course;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name="domain")
    private String domain;

    @OneToMany(mappedBy = "course",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    Set<Task> tasks = new HashSet<>();

    public Course() {
    }

    public Course(long id_professor, String code_course, int year, String name, String domain) {
        this.id_professor = id_professor;
        this.code_course = code_course;
        this.year = year;
        this.name= name;
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_professor() {
        return id_professor;
    }

    public void setId_professor(long id_professor) {
        this.id_professor = id_professor;
    }

    public String getCode_course() {
        return code_course;
    }

    public void setCode_course(String code_course) {
        this.code_course = code_course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int an) {
        this.year = an;
    }
}
