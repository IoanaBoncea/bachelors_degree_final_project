package com.ioana.backend.task;

public class CourseRequest {
    private long id_professor;
    private String code_course;
    private int year;
    private String name;
    private String domain;

    public CourseRequest(long id_professor, String code_course, int year, String name, String domain) {
        this.id_professor = id_professor;
        this.code_course = code_course;
        this.year = year;
        this.name = name;
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
