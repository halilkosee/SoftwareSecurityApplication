package com.softwaresecurityapplication.Controller;

import com.softwaresecurityapplication.Model.User;
import com.softwaresecurityapplication.Repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "SSTapi")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    private List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/{user_id}")
    private Optional<User> getUser(@PathVariable Long user_id) {
        return userRepository.findById(user_id);
    }

    @GetMapping(value = "/currentUserName")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping(value = "/currentUserId")
    public Long currentUserId(Principal principal) {
        return (userRepository.findByUsername(principal.getName())).get().getId();
    }

    @GetMapping(value = "/currentUser")
    public Optional<User> currentUser(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }



    //TODO it had better if this part would be designed

    @PostMapping("/save")
    private User create(@RequestBody final User user) {
        return userRepository.save(user);
    }
    //TODO will be designed later
    @Deprecated
    @PostMapping("/update")
    private User updateUser(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }
    //TODO will be designed later
    @Deprecated
    @PostMapping("/delete")
    private void deleteUser(@RequestBody final User user) {
        userRepository.delete(user);
    }
}
