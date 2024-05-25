package com.codesoft.udu.service.impl;

import com.codesoft.udu.model.State;
import com.codesoft.udu.repository.StateRepository;
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
class StateServiceImplTest {

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StateServiceImpl stateService;


    @Test
    void givenWrongId_whenGetRoleById_thenThrowException() {
        long id = -100L;
        String expectedMessage = "State with id " + id + " not found";

        when(stateRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> stateService.readById(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenGetRoleById_thenReturnRole() {
        State state = new State();

        when(stateRepository.findById(anyLong())).thenReturn(Optional.of(state));

        assertEquals(state, stateService.readById(state.getId()));
    }

    @Test
    void givenWrongId_whenDelete_thenThrowException() {
        long id = -100L;
        String expectedMessage = "State with id " + id + " not found";

        when(stateRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> stateService.delete(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenDelete_thenDeleteWorks() {
        when(stateRepository.findById(anyLong())).thenReturn(Optional.of(new State()));

        assertDoesNotThrow(() -> stateService.delete(1L));
        verify(stateRepository, times(1)).delete(any());
    }
}