package com.softwaresecurityapplication.Repository;

import com.softwaresecurityapplication.Enum.ERole;
import com.softwaresecurityapplication.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
