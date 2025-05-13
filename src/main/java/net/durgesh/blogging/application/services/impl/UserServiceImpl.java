package net.durgesh.blogging.application.services.impl;

import net.durgesh.blogging.application.entity.UserEntity;
import net.durgesh.blogging.application.exceptions.ResourseNotFoundException;
import net.durgesh.blogging.application.payloads.UserDTO;
import net.durgesh.blogging.application.repository.UserRepository;
import net.durgesh.blogging.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    /**
     * UserRepository is an interface that extends JpaRepository to provide CRUD operations for UserEntity.
     * It allows us to perform database operations on the 'users' table.
     *
     * @author Durgesh
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        // Assuming UserEntity has a constructor that takes the same parameters as UserDTO
        // Convert UserDTO to UserEntity
        UserEntity userEntity = this.mapToUserEntity(userDTO);

        // Save the UserEntity to the database
        UserEntity savedUserEntity = this.userRepository.save(userEntity);

        // Convert the saved UserEntity back to UserDTO and return it
        return this.mapToUserDTO(savedUserEntity);

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        // Check if the user exists in the database
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));

        // Update the user entity with the new data from userDTO
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setAbout(userDTO.getAbout());

        // Save the updated user entity to the database
        UserEntity updatedUserEntity = this.userRepository.save(userEntity);

        // Convert the updated UserEntity back to UserDTO and return it
        return this.mapToUserDTO(updatedUserEntity);

    }

    @Override
    public UserDTO getUserById(Integer userId) {
        // Check if the user exists in the database
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));

        // Convert the UserEntity to UserDTO and return it
        return this.mapToUserDTO(userEntity);

    }

    @Override
    public List<UserDTO> getAllUsers() {
        // Fetch all users from the database
        List<UserEntity> allusers = this.userRepository.findAll();

        // Convert the list of UserEntity to a list of UserDTO
        List<UserDTO> userDTOs = allusers.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());

        // Return the list of UserDTOs
        return userDTOs;
    }

    /**
     * Delete a user by ID.
     *
     * @param userId the ID of the user to be deleted
     */

    @Override
    public void deleteUser(Integer userId) {

        // Check if the user exists in the database
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));

        // Delete the user entity from the database
        this.userRepository.delete(userEntity);
    }

    /**
     * Maps a UserDTO object to a UserEntity object.
     *
     * @param userDTO the UserDTO object to be mapped
     * @return the mapped UserEntity object
     */
    private UserEntity mapToUserEntity(UserDTO userDTO) {
        // Assuming UserEntity has a constructor that takes the same parameters as UserDTO
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setAbout(userDTO.getAbout());

        return userEntity;

    }

    /**
     * Maps a UserEntity object to a UserDTO object.
     *
     * @param userEntity the UserEntity object to be mapped
     * @return the mapped UserDTO object
     */
    private UserDTO mapToUserDTO(UserEntity userEntity) {
        // Assuming UserDTO has a constructor that takes the same parameters as UserEntity
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setAbout(userEntity.getAbout());

        return userDTO;
    }
}
