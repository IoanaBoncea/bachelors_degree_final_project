package com.ioana.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "id_curs")
    private int idCurs;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "due_date")
    private Date duetDate;

    @Column(name = "status")// not started | in progress | done
    private int status;

    public Tasks() {
    }

    public Tasks(int parentId, String title, String description, int idCurs, Date startDate, Date duetDate, int status) {
        this.parentId = parentId;
        this.title = title;
        this.description = description;
        this.idCurs = idCurs;
        this.startDate = startDate;
        this.duetDate = duetDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDuetDate() {
        return duetDate;
    }

    public void setDuetDate(Date duetDate) {
        this.duetDate = duetDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
