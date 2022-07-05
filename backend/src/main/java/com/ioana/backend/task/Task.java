package com.ioana.backend.task;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "task",
    fetch = FetchType.LAZY,
    cascade = CascadeType.REMOVE)
    Set<AssignationStudentTask> assignation = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "title")
    private String title;

    @Size(max = 2500)
    @Column(name = "description" , length = 2500)
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "due_date")
    private Date duetDate;

    public Task() {
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

    public Task(Course course, int parentId, String title, String description, Date startDate, Date duetDate) {
        this.course = course;
        this.parentId = parentId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.duetDate = duetDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
