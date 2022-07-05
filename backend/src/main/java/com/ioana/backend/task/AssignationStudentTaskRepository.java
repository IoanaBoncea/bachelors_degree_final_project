package com.ioana.backend.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignationStudentTaskRepository extends JpaRepository<AssignationStudentTask, Long> {
}
