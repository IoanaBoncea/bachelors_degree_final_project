package com.ioana.backend.task;

public class AssignationStudentTaskRequest {
    private int status;
    private String studentComment;
    private String professorComment;

    public int getStatus() {
        return status;
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
