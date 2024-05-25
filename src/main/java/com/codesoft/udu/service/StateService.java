package com.codesoft.udu.service;

import com.codesoft.udu.model.State;

import java.util.List;

public interface StateService {
    State create(State state);
    State readById(long id);
    State update(State state);
    void delete(long id);

    State getByName(String name);
    List<State> getAll();
}
