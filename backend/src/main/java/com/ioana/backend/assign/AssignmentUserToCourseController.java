package com.ioana.backend.assign;

import com.ioana.backend.dbauthentication.Users;
import com.ioana.backend.dbauthentication.UsersRepository;
import com.ioana.backend.task.Course;
import com.ioana.backend.task.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignments")
@CrossOrigin(origins = "*")
public class AssignmentUserToCourseController {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("add")
    public ResponseEntity<Assignment> createAssignment(@RequestBody AssignmentRequest assignmentRequest){
        Users user = usersRepository.getReferenceById(assignmentRequest.getUserId());
        Course course = courseRepository.getReferenceById(assignmentRequest.getCourseId());


        Assignment assignment = new Assignment(user, course);

        return ResponseEntity.ok(assignmentRepository.save(assignment));
    }
}
