package com.softwaresecurityapplication.Service.Impl;

import com.softwaresecurityapplication.Model.File;
import com.softwaresecurityapplication.Repository.FileRepository;
import com.softwaresecurityapplication.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

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

    @Override
    public List<File> findAllFiles() {
        return null;
    }

    @Override
    public File findFileById(Long file_id) {
        return null;
    }

    @Override
    public File saveFile(File file) {
        return null;
    }

    @Override
    public File updateFile(File file) {
        return null;
    }

    @Override
    public void deleteFile(File file) {

    }
}
