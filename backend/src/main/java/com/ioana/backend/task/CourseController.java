package com.ioana.backend.task;

import com.ioana.backend.assign.Assignment;
import com.ioana.backend.assign.AssignmentRepository;
import com.ioana.backend.dbauthentication.Users;
import com.ioana.backend.dbauthentication.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class CourseController {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("courses/add")
    public ResponseEntity<?> addCourse(@RequestBody CourseRequest courseRequest) {

        System.out.println("domain " + courseRequest.getDomain());
        System.out.println("year: " + courseRequest.getYear());
        List<Users> usersList = usersRepository.findAll()
                .stream().filter(users -> users.getYear() == courseRequest.getYear())
                .filter(users -> courseRequest.getDomain().equals(users.getDomain()))
                .collect(Collectors.toList());

        System.out.println("size userList: " + usersList.size());
        System.out.println(usersList);

        Course saveCourse = courseRepository.save(new Course(
                courseRequest.getId_professor(),
                courseRequest.getCode_course(),
                courseRequest.getYear(),
                courseRequest.getName(),
                courseRequest.getDomain()
        ));

        List<Assignment> assignmentList = usersList
                .stream().map(users -> new Assignment(users, saveCourse)).collect(Collectors.toList());

        System.out.println(assignmentList.size());
        for(Assignment assignment : assignmentList){
            System.out.println("0: " + assignment.toString());
            System.out.println("1: " + assignment.getCourse().getName());
            System.out.println("2: " + assignment.getUser().getFirstName());
        }

        return ResponseEntity.ok(assignmentRepository.saveAll(assignmentList));
    }
}
