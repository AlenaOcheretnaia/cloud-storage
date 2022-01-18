package com.netology.aloch.controller;

import com.google.gson.Gson;
import com.netology.aloch.auth.JwtTokenUtil;
import com.netology.aloch.exceptions.ErrorInputData;
import com.netology.aloch.model.ErrorApp;
import com.netology.aloch.model.ResponseMessage;
import com.netology.aloch.model.FileForList;
import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class FilesController {

    private FileService fileService;

    @Autowired
    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from Cloud Storage by AlOch";
    }

    // *** Get list of files ***
    @GetMapping("/list")
    public ResponseEntity getListFiles(@RequestParam int limit) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<FileMyDB> files = fileService.getFilesByUser(userName);
        List<FileForList> filesList = new ArrayList<>();
        for (int i = 0; ((i < files.size()) && (i < limit)); i++) {
            filesList.add(new FileForList(files.get(i).getFilename(), files.get(i).getData().length));
        }
        String resp = new Gson().toJson(filesList);
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    // *** Upload file to Server ***
    @PostMapping("/file")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("filename") String filename,
                                                      @RequestPart("file") MultipartFile file) {
        if (filename.isEmpty() || file.isEmpty()) {
            throw new ErrorInputData("Error Input Data");
        }

        String message = "";
        try {
            fileService.store(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            message = "Could not upload the file: " + filename + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    // *** Edit Filename ***
    @PutMapping("/file")
    public void editFilename(@RequestParam("filename") String oldFilename,
                             @RequestBody HashMap<String, String> values) {

        String newFilename = values.get("filename");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        fileService.editFilename(oldFilename, newFilename, userName);
    }

    // *** Download file from server ***
    @GetMapping("/file")
    public ResponseEntity<?> getFile(@RequestParam String filename) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        FileMyDB fileDB = fileService.getFile(filename, userName);
        if (fileDB == null) {
            String errResp = new Gson().toJson(new ErrorApp("Error Input Data", 400));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
        } else {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFilename() + "\"")
                    .body(fileDB.getData());
        }
    }

    // *** Delete file from server ***
    @DeleteMapping("/file")
    public void deleteFile(@RequestParam String filename) {

        if (filename.isEmpty()) {
            throw new ErrorInputData("Error Input Data");
        }

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        fileService.deleteFileByFilename(filename, userName);
    }
}
