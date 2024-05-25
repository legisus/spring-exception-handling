package com.codesoft.udu.service.impl;

import com.codesoft.udu.exception.NullEntityReferenceException;
import com.codesoft.udu.model.User;
import com.codesoft.udu.repository.UserRepository;
import com.codesoft.udu.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (user == null) throw new NullEntityReferenceException("Cannot create null user");
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User update(User user) {
        if(user == null) throw new NullEntityReferenceException("Cannot update null user");
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        if(readById(id) == null) throw new EntityNotFoundException("User with id " + id + " not found");
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

}
