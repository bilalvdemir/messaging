package com.netas.message.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.netas.message.model.User;

public interface UserRepository extends MongoRepository<User, Long> {

    // User findById(Long id);

    User findByUsername(String name);

    User findByUsernameAndPassword(String name, String password);

    void deleteByUsername(String username);

    // void deleteUserById(Long id);
}
