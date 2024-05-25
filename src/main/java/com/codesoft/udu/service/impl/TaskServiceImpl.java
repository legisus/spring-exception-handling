package com.codesoft.udu.service.impl;

import com.codesoft.udu.exception.NullEntityReferenceException;
import com.codesoft.udu.model.Task;
import com.codesoft.udu.repository.TaskRepository;
import com.codesoft.udu.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task user) {
        if (user == null) throw new NullEntityReferenceException("Cannot create null task");
        return taskRepository.save(user);
    }

    @Override
    public Task readById(long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public Task update(Task task) {
        if (task == null) throw new NullEntityReferenceException("Cannot update null task");
        return taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        if (readById(id) == null) throw new EntityNotFoundException("Task with id " + id + " not found");
        Task task = readById(id);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        List<Task> tasks = taskRepository.getByTodoId(todoId);
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }
}
