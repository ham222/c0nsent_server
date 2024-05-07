package com.consent.server.services.impl;

import com.consent.server.model.User;
import com.consent.server.repository.interfaces.UserRepository;
import com.consent.server.services.interfaces.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws IllegalArgumentException {
        if(anyParameterInvalid(user.getName(), user.getPassword(), user.getEmail(), user.getGender())){
            throw new IllegalArgumentException();
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    private boolean anyParameterInvalid(String name, String password, String email, String gender){
        if (
                name == null || name.isEmpty() || name.length()>64 ||
                        password == null || password.length()<8 || password.length()>64 ||
                        ! EmailValidator.getInstance().isValid(email) || email.length()>128 ||
                        gender == null || gender.length() != 1){
            return true;
        }

        return false;
    }

}
