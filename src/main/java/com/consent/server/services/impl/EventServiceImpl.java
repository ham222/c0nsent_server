package com.consent.server.services.impl;

import com.consent.server.model.Action;
import com.consent.server.services.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventServiceImpl implements EventService{

    //@Autowired
    //ActionRepository repository;

    @Override
    public void processAction(Action action) throws IOException {
        // ProcessImage.encodeImageToDatabase(action.getPhoto());
        //TODO validate action
    }
}
