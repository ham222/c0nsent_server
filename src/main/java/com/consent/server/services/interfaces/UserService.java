package com.consent.server.services.interfaces;

import com.consent.server.model.User;

import java.util.Optional;

public interface UserService{

    User createUser(User user) throws IllegalArgumentException;

    Optional<User> getUserById(Long id);
}
