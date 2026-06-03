package com.devofthefour.taskboard.user.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devofthefour.taskboard.entity.User;
import com.devofthefour.taskboard.user.controller.UserRequest;
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

    public User create(UserRequest request) {
        User user = new User();
        applyRequest(user, request);
        if (user.getRegistrationDate() == null) {
            user.setRegistrationDate(LocalDate.now());
        }
        return userRepository.create(user);
    }

    public Optional<User> update(Long id, UserRequest request) {
        return userRepository.findById(id).map(existing -> {
            LocalDate registrationDate = existing.getRegistrationDate();
            applyRequest(existing, request);
            if (existing.getRegistrationDate() == null) {
                existing.setRegistrationDate(registrationDate);
            }
            return userRepository.save(existing);
        });
    }

    private void applyRequest(User user, UserRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRegistrationDate(request.getRegistrationDate());
        user.setRole(request.getRole());
    }

    public boolean delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
