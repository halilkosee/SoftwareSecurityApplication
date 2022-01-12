package com.softwaresecurityapplication.Service.Impl;

import com.softwaresecurityapplication.Model.File;
import com.softwaresecurityapplication.Model.Payload.response.AnalyseResult;
import com.softwaresecurityapplication.Model.User;
import com.softwaresecurityapplication.Repository.FileRepository;
import com.softwaresecurityapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileServiceImpl {

    private final Path root = Paths.get("uploads");
    final String riskyWords[] = {"sql","password","CREATE","UPDATE","INSERT","DELETE","DB","Database","username","gsm","email"};

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

    public List<AnalyseResult> analyse(String fileName) throws IOException {
       File file = getFileRelatedWithUser(fileName);
       List<AnalyseResult> content = readFileContent(file);
       return content;
    }

    private List<AnalyseResult> readFileContent (File file) throws IOException {
        Path path = Path.of(file.getFilePath());
        List<AnalyseResult> results = displayMapAndGetResults((HashMap<Integer, String>) readFileContentToMap(path));
        return results;
    }

    public Map<Integer,String> readFileContentToMap(Path path) throws IOException {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        String line;
        int line_index = 0;
        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
        while ((line = reader.readLine()) != null)
        {
            map.put(line_index, line);
            line_index++;
        }
        reader.close();
        return  map;
    }

    private List<AnalyseResult> displayMapAndGetResults(HashMap<Integer, String> map) {
        List<AnalyseResult> results = new ArrayList<>();
        for (int key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
            if (checkLine(map.get(key))){
                results.add(convertAnalyseResult(key,map.get(key),"string"));
            }
        }
       return results;
    }

    private AnalyseResult convertAnalyseResult(int key, String s, String risk) {
        AnalyseResult analyseResult = new AnalyseResult(calculateRiskPercentage(1,100),risk,key,s);
        return analyseResult;
    }
    private double calculateRiskPercentage(int upper,int lower) {
        return (double) (Math.random() * (upper - lower)) + lower;
    }


    private boolean checkLine(String line) {
        for (String k: riskyWords) {
            if (line.contains(k)){
                return true;
            }
        }
        return false;
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
