package com.softwaresecurityapplication.Service;

import com.softwaresecurityapplication.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long user_id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    Boolean existsByUsername(String userName);
    Boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
}
