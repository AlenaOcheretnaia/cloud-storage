package com.netology.aloch.service;

import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.repository.FileRepository;
import com.netology.aloch.repository.TokenRepository;
import com.netology.aloch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public void store(MultipartFile file, String token) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String userName = tokenRepository.findByToken(token).get().getUsername();

        FileMyDB FileDB = new FileMyDB(fileName, file.getContentType(), file.getBytes(), userName);
        fileRepository.save(FileDB);
    }

    public FileMyDB getFile(String filename, String username) {
        List<FileMyDB> fileList = fileRepository.findByFilenameAndUsername(filename, username);
        return fileList.get(0);
    }

    public List<FileMyDB> getFilesByUser(String userName) {
        return fileRepository.findByUsername(userName);
    }
}
