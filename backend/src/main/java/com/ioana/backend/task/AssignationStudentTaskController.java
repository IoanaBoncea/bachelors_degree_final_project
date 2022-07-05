package com.ioana.backend.task;

import com.ioana.backend.dbauthentication.Users;
import com.ioana.backend.dbauthentication.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.el.ELException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class AssignationStudentTaskController {

    @Autowired
    AssignationStudentTaskRepository assignationStudentTaskRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/assignation/add")
    public ResponseEntity<?> addElementsOnAssignationUserTask(@RequestBody AssignationTaskRequest assignationStudentTask){
        Task task = taskRepository.findById(assignationStudentTask.getTaskId())
                        .orElseThrow(ELException::new);

        Users user = usersRepository.findById(assignationStudentTask.getUserId())
                        .orElseThrow(ELException::new);

        return ResponseEntity.ok(
                assignationStudentTaskRepository.save(new AssignationStudentTask(
                        user,
                        task
                )));
    }
//
//    @PostMapping("/assignation/update/{}")
//    public ResponseEntity<?> updateElementsOnAssignationTask(){
//
//    }
}
