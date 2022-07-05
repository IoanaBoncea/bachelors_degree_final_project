package com.ioana.backend.task;

import com.ioana.backend.dbauthentication.Users;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourse();
    Course findById(long id);
    Course insert(Course course);
    boolean delete(long id);
    boolean update(Course course);
}
