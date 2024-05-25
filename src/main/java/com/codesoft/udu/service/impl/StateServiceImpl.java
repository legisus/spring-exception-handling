package com.codesoft.udu.service.impl;

import com.codesoft.udu.exception.NullEntityReferenceException;
import com.codesoft.udu.model.State;
import com.codesoft.udu.repository.StateRepository;
import com.codesoft.udu.service.StateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State create(State state) {
        if (state == null) throw new NullEntityReferenceException("Cannot create null state");
        return stateRepository.save(state);
    }

    @Override
    public State readById(long id) {
        return stateRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("State with id " + id + " not found"));
    }

    @Override
    public State update(State state) {
        if (state == null) throw new NullEntityReferenceException("Cannot update null state");
        return stateRepository.save(state);
    }

    @Override
    public void delete(long id) {
        if (readById(id) == null) throw new EntityNotFoundException("State with id " + id + " not found");
        State state = readById(id);
        stateRepository.delete(state);
    }

    @Override
    public State getByName(String name) {
        Optional<State> optional = Optional.ofNullable(stateRepository.getByName(name));
        return optional.orElseThrow(() -> new EntityNotFoundException("State with name " + name + " not found"));
    }

    @Override
    public List<State> getAll() {
        List<State> states = stateRepository.getAll();
        return states.isEmpty() ? new ArrayList<>() : states;
    }
}
