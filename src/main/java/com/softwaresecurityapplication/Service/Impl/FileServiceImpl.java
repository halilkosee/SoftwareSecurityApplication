package com.softwaresecurityapplication.Service.Impl;

import com.softwaresecurityapplication.Model.File;
import com.softwaresecurityapplication.Model.Role;
import com.softwaresecurityapplication.Model.User;
import com.softwaresecurityapplication.Repository.FileRepository;
import com.softwaresecurityapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileServiceImpl {

    private final Path root = Paths.get("uploads");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserServiceImpl userService;

    public void save(MultipartFile file) throws IOException {
        File fileEntity = new File();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        final Path path = Paths.get(generatePath());

        try {
            Files.copy(file.getInputStream(), path.resolve(fileEntity.getName()));
            fileEntity.setFilePath(String.valueOf(path.resolve(fileEntity.getName())));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        saveFileAndUpdateUser(fileEntity);
    }

    public File analyse(String fileName) throws IOException{
       File file = getFileRelatedWithUser(fileName);
       String content = readFileContent(file);
       System.out.print(content);
       return file;
    }

    private String readFileContent (File file) throws IOException {
        Path path = Path.of(file.getFilePath());
        String actual = Files.readString(path);
        return actual;
    }

    public List<File> getUserRelatedFiles() {
        User user = userService.currentUser();
        List<File> fileList = fileRepository.findAll();
        List<File> fileList1 = new ArrayList<>();
        for (File f: fileList) {
            if(f.getFilePath().contains(user.getUsername())){
                fileList1.add(f);
            }
        }
        return fileList1;
    }

    public File getFileRelatedWithUser(String filename) {
        List<File> fileList = getUserRelatedFiles();
        for (File f: fileList) {
            if(f.getName().equals(filename)){
                return f;
            }
        }
        return  null;
    }

    private void saveFileAndUpdateUser(File fileEntity) {
        fileRepository.save(fileEntity);
        User user = userService.currentUser();
        Set<File> userFiles = (Set<File>) user.getFiles();
        userFiles.add(fileEntity);
        user.setFiles(userFiles);
        userService.updateUser(user);
    }

    private String generatePath() throws IOException {
        Path path = Paths.get("uploads",userService.curentUserName());
        if(Files.notExists(root)){
            Files.createDirectory(root);
        }
        if(Files.notExists(path)){
            Files.createDirectory(path);
        }
        return String.valueOf(path);
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
