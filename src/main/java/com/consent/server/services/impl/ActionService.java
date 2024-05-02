package com.consent.server.services.impl;

import com.consent.server.model.Action;
import com.consent.server.repository.interfaces.ActionRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ActionService implements com.consent.server.services.interfaces.ActionService{

    @Autowired
    ActionRepository repository;

    @Override
    public void processAction(Action action) throws IOException {
        ProcessImage.encodeImageToDatabase(action.getPhoto());
        //TODO validate action
    }
}
