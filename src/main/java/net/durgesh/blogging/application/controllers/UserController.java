package net.durgesh.blogging.application.controllers;

import net.durgesh.blogging.application.payloads.UserDTO;
import net.durgesh.blogging.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// The @RestController annotation is a convenience annotation that combines @Controller and @ResponseBody
// It indicates that the class is a controller where every method returns a domain object instead of a view
@RestController
// The @RequestMapping annotation is used to specify the base URL for all the endpoints in this controller
// In this case, all the endpoints in this controller will start with "/api/users"
@RequestMapping("/api/users")
public class UserController {

    // The @Autowired annotation is used to automatically inject the UserService bean into this controller
    // The UserService class is a service layer that contains business logic related to user operations
    @Autowired
    private UserService userService;

    // Add a user
    // The UserDTO object is expected to be in the request body
    // The @RequestBody annotation is used to indicate that the method parameter should be bound to the body of the web request
    // The UserDTO object is a data transfer object(DTO) that contains user details
    // The @PostMapping annotation is used to map HTTP POST requests onto specific handler methods
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        // The createUser method in the UserService class is called to create a new user
        // The UserDTO object is passed as an argument to this method
        // The createUser method is expected to return a UserDTO object representing the created user
        // The UserDTO object is a data transfer object that contains user details
        UserDTO createdUserDTO = userService.createUser(userDTO);
        // The ResponseEntity class is used to represent the entire HTTP response
        // including status code, headers, and body
        // The HttpStatus.CREATED constant represents the HTTP status code 201
        // which indicates that a new resource has been successfully created
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);

    }



    // Update a user
    // Get a user by ID
    // Get all users
    // Delete a user

}
