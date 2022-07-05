package com.ioana.backend.task;

import com.ioana.backend.assign.Assignment;
import com.ioana.backend.assign.AssignmentRepository;
import com.ioana.backend.dbauthentication.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.el.ELException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    AssignationStudentTaskRepository assignationStudentTaskRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasksForUser() {
        return taskRepository.findAll();
    }

    //@GetMapping("/tasks/{idUSer}/{idTask}")
    public List<AssignationStudentTask> changeTaskStatusFindById(/*@PathVariable("idTask")*/ long taskId,
            /*@PathVariable("idUser")*/ long idUser
            /*@PathVariable("status")*/) {
        return assignationStudentTaskRepository.findAll()
                .stream()
                .filter(assignationStudentTask -> assignationStudentTask.getTask().getId() == taskId)
                .filter(assignationStudentTask -> assignationStudentTask.getUser().getId() == idUser)
                .collect(Collectors.toList());
    }

    @PostMapping("/tasks/{idAssignation}")
    public ResponseEntity<AssignationStudentTask> changeTaskStatus(@PathVariable("idAssignation") long assignationId,
                                                                   @RequestBody AssignationStudentTaskRequest assignationStudentTaskRequest) {

        AssignationStudentTask assignationStudentTask = assignationStudentTaskRepository.findById(assignationId)
                .orElseThrow(ELException::new);

        assignationStudentTask.setStatus(assignationStudentTaskRequest.getStatus());

        return ResponseEntity.ok(assignationStudentTaskRepository.save(assignationStudentTask));
    }

    @PostMapping("/tasks/assignation/{idAssignation}")
    public ResponseEntity<AssignationStudentTask> addCommentFromStudent(@PathVariable("idAssignation") long assignationId,
                                                                        @RequestBody AssignationStudentTaskRequest assignationStudentTaskRequest) {
        AssignationStudentTask assignationStudentTask = assignationStudentTaskRepository.findById(assignationId)
                .orElseThrow(ELException::new);

        assignationStudentTask.setStudentComment(assignationStudentTaskRequest.getStudentComment());

        return ResponseEntity.ok(assignationStudentTaskRepository.save(assignationStudentTask));
    }

    //assignationStudentTask.setProfessorComment(assignationStudentTaskRequest.getProfessorComment());
    @PostMapping("/tasks/assignationProfessor/{idAssignation}")
    public ResponseEntity<AssignationStudentTask> addCommentFromProfessor(@PathVariable("idAssignation") long assignationId,
                                                                        @RequestBody AssignationStudentTaskRequest assignationStudentTaskRequest) {
        AssignationStudentTask assignationStudentTask = assignationStudentTaskRepository.findById(assignationId)
                .orElseThrow(ELException::new);

        assignationStudentTask.setProfessorComment(assignationStudentTaskRequest.getProfessorComment());
        return ResponseEntity.ok(assignationStudentTaskRepository.save(assignationStudentTask));
    }


    @PostMapping("/tasks/update/{taskId}")
    public ResponseEntity<?> taskUpdate(@PathVariable("taskId") long taskId,
                                        @RequestBody Task task) {
        Task oldTask = taskRepository.findById(taskId)
                .orElseThrow(ELException::new);

        if (task.getDescription() != null)
            oldTask.setDescription(task.getDescription());

        if (task.getDuetDate() != null)
            oldTask.setDuetDate(task.getDuetDate());

        if (task.getStartDate() != null)
            oldTask.setStartDate(task.getStartDate());

        if (task.getTitle() != null)
            oldTask.setTitle(task.getTitle());

        System.out.println(task.getTitle() + " " + task.getDescription());

        return ResponseEntity.ok(taskRepository.save(oldTask));
    }

    @DeleteMapping("/tasks/delete/{taskId}")
    private String deleteTask(@PathVariable("taskId") long taskId) {
        List<AssignationStudentTask> assignationStudentTaskList = assignationStudentTaskRepository
                .findAll()
                .stream()
                .filter(assignationStudentTask -> assignationStudentTask.getTask()
                        .getId() == taskId)
                .collect(Collectors.toList());
        for (AssignationStudentTask assignationStudentTask : assignationStudentTaskList) {
            assignationStudentTaskRepository.delete(assignationStudentTask);
        }
        taskService.delete(taskId);
        return "Task with id: " + taskId + " deleted";
    }

    @PostMapping("/tasks/add")
    public ResponseEntity<List<AssignationStudentTask>> addTask(@RequestBody AddTaskRequest taskRequest) {

        List<Users> assignmentList = assignmentRepository.findAll()
                .stream()
                .filter(assignment -> assignment.getCourse()
                        .getId() == taskRequest.getCourseId())
                .map(Assignment::getUser)
                .collect(Collectors.toList());

        Course course = courseRepository.findById(taskRequest.getCourseId())
                .orElseThrow(ELException::new);

        Task savedTask = taskRepository.save(new Task(
                course,
                -1,
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                taskRequest.getStartDate(),
                taskRequest.getDueDate()
        ));

        List<AssignationStudentTask> assignationStudentTaskList = assignmentList
                .stream().map(user -> new AssignationStudentTask(user, savedTask)).collect(Collectors.toList());

        return ResponseEntity.ok(assignationStudentTaskRepository.saveAll(assignationStudentTaskList));

    }

    public List<Assignment> getAllAssignmentsForUser(@PathVariable(value = "id") Long userId) {
        return this.assignmentRepository
                .findAll()
                .stream()
                .filter(assignment -> assignment.getUser().getId() == userId)
                .collect(Collectors.toList());
    }


    public Set<Task> getAllTasksForStudentId(@PathVariable(value = "idUser") Long idUser) {
        LinkedHashSet<Task> result = new LinkedHashSet<>();
        List<Assignment> assignmentList;

        assignmentList = assignmentRepository
                .findAll()
                .stream()
                .filter(assignment -> assignment.getUser().getId() == idUser)
                .collect(Collectors.toList());

        List<Task> taskListAll = getAllTasksForUser();
        for (Assignment assignment : assignmentList) {
            for (Task task : taskListAll) {
                if (assignment.getCourse().getId() == task.getCourse().getId()) {
                    result.add(task);
                }
            }
        }
        return result;
    }


    @GetMapping("/tasks/{idUser}")
    public HashSet<AssignationStudentTask> getAllTasksDetailsForUser(@PathVariable(value = "idUser") Long idUser) {

        System.out.println("E bine bine");
        HashSet<AssignationStudentTask> result = new HashSet<>();

        List<AssignationStudentTask> assignationStudentTasks = assignationStudentTaskRepository.findAll()
                .stream()
                .filter(assignationStudentTask -> Objects.equals(assignationStudentTask.getUser().getId(), idUser))
                .collect(Collectors.toList());

        Set<Task> taskList = getAllTasksForStudentId(idUser);

        for (AssignationStudentTask assignationStudent : assignationStudentTasks) {
            for (Task assignationTask : taskList) {
                if (assignationStudent.task == assignationTask)
                    result.add(assignationStudent);
            }
        }
        return result;
    }

    @GetMapping("/course/student/{studentID}")
    public Set<Course> getAllCourseForStudent(@PathVariable(value = "studentID") Long studentID) {
        Set<Course> result = new HashSet<>();

        List<Assignment> assignmentList = assignmentRepository
                .findAll()
                .stream()
                .filter(assignment -> assignment.getUser().getId() == studentID)
                .collect(Collectors.toList());
        List<Course> allCourseSet = courseRepository.findAll();

        System.out.println("152" + assignmentList);
        System.out.println("153" + allCourseSet);

        for (Assignment assignment : assignmentList) {
            for (Course course : allCourseSet) {
                if (course.getId() == assignment.getCourse().getId()) {
                    result.add(course);
                }
            }
        }
        return result;
    }

    //user that have a task in a specific course
    @GetMapping("/tasks/{idUser}/{professorId}")
    public List<AssignationStudentTask> getAllTasksDetailsForUserFromCourse(@PathVariable(value = "idUser") Long idUser, @PathVariable(value = "professorId") Long professorId) {
        List<AssignationStudentTask> result = new ArrayList<>();
        HashSet<AssignationStudentTask> intermediate = new HashSet<>();
        List<Course> courseOfTeacher = getAllCourseOfProfessorId(professorId);

        //asignarea cursuri - student idStudent
        List<AssignationStudentTask> assignationStudentTasks = assignationStudentTaskRepository.findAll()
                .stream()
                .filter(assignationStudentTask -> idUser.equals(assignationStudentTask.getUser().getId()))
                .collect(Collectors.toList());

        for (AssignationStudentTask assignationStudentTask : assignationStudentTasks) {
            for (Course course : courseOfTeacher) {
                if (assignationStudentTask.getTask().getCourse().getId() == course.getId()) {
                    result.add(assignationStudentTask);
                }
            }
        }
        return result;
    }

    //al tasks -  professor with {professorId}
    @GetMapping("/tasks/professor/{professorId}")
    public List<Task> tasksOfSpecificTeacher(@PathVariable(value = "professorId") Long professorId) {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getCourse().getId_professor() == professorId)
                .collect(Collectors.toList());
    }

    @GetMapping("/course/{professorId}")
    public List<Course> getAllCourseOfProfessorId(@PathVariable(value = "professorId") Long professorId) {
        return this.courseRepository.findAll()
                .stream()
                .filter(course -> course.getId_professor() == professorId)
                .collect(Collectors.toList());
    }
}
