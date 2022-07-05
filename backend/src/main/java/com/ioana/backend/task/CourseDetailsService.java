package com.ioana.backend.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseDetailsService implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourse() {
        return  (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(long id) {
        Optional<Course> result = courseRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Course insert(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean delete(long id) {
        try {
            courseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Course course) {
        try {
            courseRepository.save(course);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
