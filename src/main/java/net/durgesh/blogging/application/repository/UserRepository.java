package net.durgesh.blogging.application.repository;

import net.durgesh.blogging.application.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository is an interface that extends JpaRepository to provide CRUD operations for UserEntity.
 * It allows us to perform database operations on the 'users' table.
 *
 * @author Durgesh
 */
// @Repository annotation is not required as JpaRepository already has it
// and Spring Data JPA will automatically detect it.
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
