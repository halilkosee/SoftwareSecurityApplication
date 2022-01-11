package com.softwaresecurityapplication.Service.Impl;

import com.softwaresecurityapplication.Model.User;
import com.softwaresecurityapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long user_id) {
        return userRepository.getById(user_id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
