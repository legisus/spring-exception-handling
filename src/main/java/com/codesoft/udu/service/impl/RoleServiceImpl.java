package com.codesoft.udu.service.impl;

import com.codesoft.udu.exception.NullEntityReferenceException;
import com.codesoft.udu.model.Role;
import com.codesoft.udu.repository.RoleRepository;
import com.codesoft.udu.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        if (role == null) throw new NullEntityReferenceException("Cannot create null role");
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Role with id " + id + " not found"));
    }

    @Override
    public Role update(Role role) {
        if (role == null) throw new NullEntityReferenceException("Cannot update null role");
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) {
        if (readById(id) == null) throw new EntityNotFoundException ("Role with id " + id + " not found");
        Role role = readById(id);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
