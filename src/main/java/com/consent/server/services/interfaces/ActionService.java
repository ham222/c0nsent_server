package com.consent.server.services.interfaces;

import com.consent.server.model.Action;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ActionService{
    public void processAction(Action action) throws IOException;
}
