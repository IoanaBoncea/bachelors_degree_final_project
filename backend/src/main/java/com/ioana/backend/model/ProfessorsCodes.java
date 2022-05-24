package com.ioana.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "professors_codes")
public class ProfessorsCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;
}
