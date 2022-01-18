package com.softwaresecurityapplication.Controller;

import com.softwaresecurityapplication.Model.File;
import com.softwaresecurityapplication.Model.Payload.request.FileAnalyseRequest;
import com.softwaresecurityapplication.Model.Payload.response.AnalyseResult;
import com.softwaresecurityapplication.Model.Payload.response.FileResponse;
import com.softwaresecurityapplication.Service.Impl.FileServiceImpl;
import com.softwaresecurityapplication.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/file")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileContoller {

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }
    @PostMapping("analyse")
    public List<AnalyseResult> analyse(@RequestBody FileAnalyseRequest fileAnalyseRequest) {
        try {
            return fileService.analyse(fileAnalyseRequest.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("userFiles")
    public List<FileResponse> listRelatedWithUser() {
        return fileService.getUserRelatedFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<FileResponse> list() {
        return fileService.getUserRelatedFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(File fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileEntity.getId())
                .toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(fileEntity.getId());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<File> fileEntityOptional = fileService.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        File fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }
}
