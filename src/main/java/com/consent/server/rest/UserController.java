package com.consent.server.rest;

import com.consent.server.model.User;
import com.consent.server.repository.interfaces.UserRepository;
import com.consent.server.services.interfaces.UserService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {

        //Read response from JSON
        try{
            logger.info(user.getEmail());
            userService.createUser(user);
        }catch (IllegalArgumentException e){
            logger.error("Couldn't create user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.error("User {} created",user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}