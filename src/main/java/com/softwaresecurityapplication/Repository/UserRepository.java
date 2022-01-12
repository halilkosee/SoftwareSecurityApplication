package com.softwaresecurityapplication.Repository;

import com.softwaresecurityapplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
