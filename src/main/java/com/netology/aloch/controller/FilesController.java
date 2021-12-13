package com.netology.aloch.controller;

import com.google.gson.Gson;
import com.netology.aloch.exceptions.BadCredentials;
import com.netology.aloch.exceptions.ErrorInputData;
import com.netology.aloch.exceptions.UnauthorizedError;
import com.netology.aloch.model.ResponseMessage;
import com.netology.aloch.model.FileForList;
import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.service.FileService;
import com.netology.aloch.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FilesController {

    @Autowired
    private FileService fileService;
    @Autowired
    private TokenService tokenService;

    public FilesController(FileService fileStorageService) {
        this.fileService = fileStorageService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from Cloud Storage by AlOch";
    }

    // *** Get list of files ***
    @GetMapping("/list")
    public ResponseEntity getListFiles(@RequestParam int limit,
                                       @RequestHeader("auth-token") String token) {

        String userName = tokenService.findUserByToken(token);
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
                                                      @RequestPart("file") MultipartFile file,
                                                      @RequestHeader("auth-token") String token) {
        if (filename.isEmpty() || file.isEmpty()) {
            throw new ErrorInputData("Error Input Data");
        } else if (token.isEmpty()) {
            throw new UnauthorizedError("Unauthorized error");
        }

        String message = "";
        try {
            fileService.store(file, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the file: " + filename + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    // *** Edit Filename ***
    @PutMapping("/file")
    public ResponseEntity editFilename(@RequestParam("filename") String oldFilename,
                                       @RequestHeader("auth-token") String token,
                                       @RequestBody HashMap<String, String> values) {
        String newFilename = values.get("filename");
        String username = tokenService.findUserByToken(token);
        fileService.editFilename(oldFilename, newFilename, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // *** Download file from server ***
    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile(@RequestParam String filename,
                                          @RequestHeader("auth-token") String token) {
        String username = tokenService.findUserByToken(token);
        FileMyDB fileDB = fileService.getFile(filename, username);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFilename() + "\"")
                .body(fileDB.getData());
    }

    // *** Delete file from server ***
    @DeleteMapping("/file")
    public ResponseEntity deleteFile(@RequestParam String filename,
                                     @RequestHeader("auth-token") String token) {
        if (filename.isEmpty()) {
            throw new ErrorInputData("Error Input Data");
        } else if (token.isEmpty()) {
            throw new UnauthorizedError("Unauthorized error");
        }
        String username = tokenService.findUserByToken(token);
        // error 500
        fileService.deleteFileByFilename(filename, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
