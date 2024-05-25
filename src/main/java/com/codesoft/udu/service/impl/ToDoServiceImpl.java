package com.codesoft.udu.service.impl;

import com.codesoft.udu.exception.NullEntityReferenceException;
import com.codesoft.udu.model.ToDo;
import com.codesoft.udu.repository.ToDoRepository;
import com.codesoft.udu.service.ToDoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository todoRepository;

    public ToDoServiceImpl(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo create(ToDo todo) {
        if (todo == null) throw new NullEntityReferenceException("Cannot create null todo");
        return todoRepository.save(todo);
    }

    @Override
    public ToDo readById(long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("ToDo with id " + id + " not found"));
    }

    @Override
    public ToDo update(ToDo todo) {
        if (todo == null) throw new NullEntityReferenceException("Cannot update null todo");
        return todoRepository.save(todo);
    }

    @Override
    public void delete(long id) {
        if (readById(id) == null) throw new EntityNotFoundException("ToDo with id " + id + " not found");
        ToDo todo = readById(id);
        todoRepository.delete(todo);
    }

    @Override
    public List<ToDo> getAll() {
        List<ToDo> todos = todoRepository.findAll();
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        List<ToDo> todos = todoRepository.getByUserId(userId);
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }
}
