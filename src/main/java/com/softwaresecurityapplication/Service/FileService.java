package com.softwaresecurityapplication.Service;


import com.softwaresecurityapplication.Model.File;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface FileService {

    void save(MultipartFile file) throws IOException;

    Optional<File> getFile(String id);

    List<File> getAllFiles();

    List<File> findAllFiles();

    File findFileById(Long file_id);

    File saveFile(File file);

    File updateFile(File file);

    void deleteFile(File file);
}
