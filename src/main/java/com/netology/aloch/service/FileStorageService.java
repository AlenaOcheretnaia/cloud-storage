package com.netology.aloch.service;

import com.netology.aloch.entity.FileMyDB;
import com.netology.aloch.repository.CloudStorageRepository;
import com.netology.aloch.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private CloudStorageRepository cloudStorageRepository;

    public FileMyDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileMyDB FileDB = new FileMyDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(FileDB);
    }

//    public FileMyDB getFile(String name) {
//        String fileId = cloudStorageRepository.getFileIdByName(name);
//        return fileDBRepository.findById(fileId).get();
//    }

    public Stream<FileMyDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
