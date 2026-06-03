package com.devofthefour.taskboard.user.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devofthefour.taskboard.entity.User;
import com.devofthefour.taskboard.user.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(User user) {
        if (user.getRegistrationDate() == null) {
            user.setRegistrationDate(LocalDate.now());
        }
        return userRepository.create(user);
    }

    public Optional<User> update(Long id, User user) {
        return userRepository.findById(id).map(existing -> {
            if (user.getRegistrationDate() == null) {
                user.setRegistrationDate(existing.getRegistrationDate());
            }
            user.setId(id);
            return userRepository.save(user);
        });
    }

    public boolean delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
