package com.ioana.backend.task;

import com.ioana.backend.dbauthentication.Users;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "assignments_user_task")
public class AssignationStudentTask {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Users user;

    @ManyToOne
    @JoinColumn(name = "id_task")
    Task task;

    @Column
    private int status;

    @Size(max = 2500)
    @Column(name = "student_comment" , length = 2500)
    private String studentComment;

    @Size(max = 2500)
    @Column(name = "professor_comment" , length = 2500)
    private String professorComment;

    public AssignationStudentTask() {
    }

    public int getStatus() {
        return status;
    }

    public AssignationStudentTask(Users user, Task task) {
        this.user = user;
        this.task = task;
        this.status = 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public String getProfessorComment() {
        return professorComment;
    }

    public void setProfessorComment(String professorComment) {
        this.professorComment = professorComment;
    }
}
