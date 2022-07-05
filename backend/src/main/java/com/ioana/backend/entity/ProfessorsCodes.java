package com.ioana.backend.entity;

import com.ioana.backend.dbauthentication.Users;

import javax.persistence.*;

@Entity
@Table(name = "professors")
public class ProfessorsCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @OneToOne(mappedBy = "professorsCodes")
    private Users professor;
}
