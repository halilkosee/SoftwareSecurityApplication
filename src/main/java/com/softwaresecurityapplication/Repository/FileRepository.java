package com.softwaresecurityapplication.Repository;

import com.softwaresecurityapplication.Model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String> {
}
