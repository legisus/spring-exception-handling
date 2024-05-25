package com.codesoft.udu.service.impl;

import com.codesoft.udu.model.ToDo;
import com.codesoft.udu.repository.ToDoRepository;
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
class ToDoServiceImplTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoServiceImpl toDoService;

    @Test
    void givenWrongId_whenGetRoleById_thenThrowException() {
        long id = -100L;
        String expectedMessage = "ToDo with id " + id + " not found";

        when(toDoRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> toDoService.readById(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenGetRoleById_thenReturnRole() {
        ToDo toDo = new ToDo();

        when(toDoRepository.findById(anyLong())).thenReturn(Optional.of(toDo));

        assertEquals(toDo, toDoService.readById(toDo.getId()));
    }

    @Test
    void givenWrongId_whenDelete_thenThrowException() {
        long id = -100L;
        String expectedMessage = "ToDo with id " + id + " not found";

        when(toDoRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> toDoService.delete(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenDelete_thenDeleteWorks() {
        when(toDoRepository.findById(anyLong())).thenReturn(Optional.of(new ToDo()));

        assertDoesNotThrow(() -> toDoService.delete(1L));
        verify(toDoRepository, times(1)).delete(any());
    }
}