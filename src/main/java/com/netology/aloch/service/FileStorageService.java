package com.netology.aloch.service;

import com.netology.aloch.model.FileMyDB;
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

    public FileMyDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileMyDB FileDB = new FileMyDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(FileDB);
    }

//    public FileMyDB getFile(Integer id) {
//        return fileDBRepository.findById(id).get();
//    }

    public Stream<FileMyDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
