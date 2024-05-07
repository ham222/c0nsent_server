package com.consent.server.services.interfaces;

import com.consent.server.model.Action;

import java.io.IOException;

public interface EventService{
    public void processAction(Action action) throws IOException;
}
