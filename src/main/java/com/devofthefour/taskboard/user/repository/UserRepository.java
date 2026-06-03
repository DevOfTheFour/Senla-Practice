package com.devofthefour.taskboard.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.devofthefour.taskboard.database.UserDatabase;
import com.devofthefour.taskboard.entity.User;

@Repository
public class UserRepository {
    private final UserDatabase userDatabase;

    public UserRepository(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public List<User> findAll() {
        return userDatabase.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDatabase.findById(id);
    }

    public Optional<User> findByName(String name) {
        return userDatabase.findByName(name);
    }

    public Optional<User> findByEmail(String email) {
        return userDatabase.findByEmail(email);
    }

    public User save(User user) {
        return userDatabase.save(user);
    }

    public User create(User user) {
        return userDatabase.save(user);
    }

    public void deleteById(Long id) {
        userDatabase.deleteById(id);
    }
}
