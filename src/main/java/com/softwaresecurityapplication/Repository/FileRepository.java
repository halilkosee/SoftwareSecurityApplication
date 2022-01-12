package com.softwaresecurityapplication.Repository;

import com.softwaresecurityapplication.Model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    @Query("select f.id from File f" +
            " where f.filePath like 'user_1' ")
    List<String> getFileIdsRelatedWithUser(@Param("userName") String userName);
}
