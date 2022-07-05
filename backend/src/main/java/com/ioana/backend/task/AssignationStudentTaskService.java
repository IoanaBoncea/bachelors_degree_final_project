package com.ioana.backend.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignationStudentTaskService {
    @Autowired
    AssignationStudentTaskRepository assignationRepository;
}
