package com.softwaresecurityapplication.Service.Impl;

import com.softwaresecurityapplication.Model.File;
import com.softwaresecurityapplication.Repository.FileRepository;
import com.softwaresecurityapplication.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl{

    @Autowired
    private FileRepository fileRepository;

    public void save(MultipartFile file) throws IOException {
        File fileEntity = new File();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());

        fileRepository.save(fileEntity);
    }

    public Optional<File> getFile(String id) {
        return fileRepository.findById(id);
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public List<File> findAllFiles() {
        return null;
    }

    public File findFileById(Long file_id) {
        return null;
    }

    public File saveFile(File file) {
        return null;
    }

    public File updateFile(File file) {
        return null;
    }

    public void deleteFile(File file) {

    }
}
