package com.codesoft.udu.service.impl;

import com.codesoft.udu.model.User;
import com.codesoft.udu.repository.UserRepository;
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
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void givenWrongId_whenGetRoleById_thenThrowException() {
        long id = -100L;
        String expectedMessage = "User with id " + id + " not found";

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> userService.readById(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenGetRoleById_thenReturnRole() {
        User user = new User();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        assertEquals(user, userService.readById(user.getId()));
    }

    @Test
    void givenWrongId_whenDelete_thenThrowException() {
        long id = -100L;
        String expectedMessage = "User with id " + id + " not found";

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> userService.delete(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenDelete_thenDeleteWorks() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));

        assertDoesNotThrow(() -> userService.delete(1L));
        verify(userRepository, times(1)).delete(any());
    }
}