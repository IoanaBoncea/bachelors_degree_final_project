package com.ioana.backend.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Task getTaskById(long id){
        return taskRepository.findById(id).get();
    }

    public void saveOrUpdate(Task task){
        taskRepository.save(task);
    }

    public void delete(long id){
        taskRepository.deleteById(id);
    }

    public void update(Task task, int taskId)
    {
        taskRepository.save(task);
    }
}
