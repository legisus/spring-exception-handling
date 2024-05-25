package com.codesoft.udu.service.impl;

import com.codesoft.udu.model.Task;
import com.codesoft.udu.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void givenWrongId_whenGetRoleById_thenThrowException() {
        long id = -100L;
        String expectedMessage = "Task with id " + id + " not found";

        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> taskService.readById(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenGetRoleById_thenReturnRole() {
        Task task = new Task();

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        assertEquals(task, taskService.readById(task.getId()));
    }

    @Test
    void givenWrongId_whenDelete_thenThrowException() {
        long id = -100L;
        String expectedMessage = "Task with id " + id + " not found";

        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> taskService.delete(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenDelete_thenDeleteWorks() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(new Task()));

        assertDoesNotThrow(() -> taskService.delete(1L));
        verify(taskRepository, times(1)).delete(any());
    }
}