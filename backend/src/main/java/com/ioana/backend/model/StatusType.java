package com.ioana.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "status_type")
public class StatusType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private String statusType;
}
