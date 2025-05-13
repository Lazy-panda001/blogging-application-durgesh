package net.durgesh.blogging.application.services;

import net.durgesh.blogging.application.payloads.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * Create a new user.
     *
     * @param userDTO the user data transfer object containing user details
     * @return the created user data transfer object
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Update an existing user.
     *
     * @param userDTO the user data transfer object containing updated user details
     * @param userId  the ID of the user to be updated
     * @return the updated user data transfer object
     */
    UserDTO updateUser(UserDTO userDTO, Integer userId);

    /**
     * Get a user by ID.
     *
     * @param userId the ID of the user to be retrieved
     * @return the user data transfer object containing user details
     */
    UserDTO getUserById(Integer userId);

    /**
     * Get all users.
     *
     * @return a list of user data transfer objects containing details of all users
     */
    List<UserDTO> getAllUsers();

    /**
     * Delete a user by ID.
     *
     * @param userId the ID of the user to be deleted
     */
    void deleteUser(Integer userId);
}
