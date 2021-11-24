package com.netology.aloch.controller;

import com.netology.aloch.message.ResponseFile;
import com.netology.aloch.message.ResponseMessage;
import com.netology.aloch.service.CloudStorageService;
import com.netology.aloch.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CloudStorageController {
    @Autowired
    private final CloudStorageService cloudStorageService;
    @Autowired
    private FileStorageService storageService;

    public CloudStorageController(CloudStorageService cloudStorageService, FileStorageService fileStorageService) {
        this.cloudStorageService = cloudStorageService;
        this.storageService = fileStorageService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello from Cloud Storage by AlOch";
    }

    //Upload file to Server
    @PostMapping("/file")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    //Get list of files
    @GetMapping("/list")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            return new ResponseFile(
                    dbFile.getName(),
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


}
