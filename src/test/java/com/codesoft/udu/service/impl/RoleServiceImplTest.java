package com.codesoft.udu.service.impl;

import com.codesoft.udu.model.Role;
import com.codesoft.udu.repository.RoleRepository;
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
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void givenWrongId_whenGetRoleById_thenThrowException() {
        long id = -100L;
        String expectedMessage = "Role with id " + id + " not found";

        when(roleRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> roleService.readById(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenGetRoleById_thenReturnRole() {
        Role role = new Role();

        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));

        assertEquals(role, roleService.readById(role.getId()));
    }

    @Test
    void givenWrongId_whenDelete_thenThrowException() {
        long id = -100L;
        String expectedMessage = "Role with id " + id + " not found";

        when(roleRepository.findById(anyLong())).thenReturn(Optional.empty());

        var e = assertThrows(EntityNotFoundException.class, () -> roleService.delete(id));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void givenValidId_whenDelete_thenDeleteWorks() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(new Role()));

        assertDoesNotThrow(() -> roleService.delete(1L));
        verify(roleRepository, times(1)).delete(any());
    }
}