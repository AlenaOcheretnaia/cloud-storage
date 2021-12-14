package com.netology.aloch.service;

import com.netology.aloch.auth.JwtTokenUtil;
import com.netology.aloch.exceptions.ErrorDeleteFile;
import com.netology.aloch.exceptions.ErrorInputData;
import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public void store(MultipartFile file, String token) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        if (fileRepository.findByFilenameAndUsername(fileName, userName).isEmpty()) {
            FileMyDB FileDB = new FileMyDB(fileName, file.getContentType(), file.getBytes(), userName);
            fileRepository.save(FileDB);
        } else {
            throw new ErrorInputData("Such file already uploaded");
        }
    }

    public FileMyDB getFile(String filename, String username) {
        List<FileMyDB> fileList = fileRepository.findByFilenameAndUsername(filename, username);
        if (fileList.isEmpty()) {
            new ErrorInputData("No file with such name");
            return null;
        } else {
            return fileList.get(0);
        }
    }

    public List<FileMyDB> getFilesByUser(String userName) {
        return fileRepository.findByUsername(userName);
    }

    public void deleteFileByFilename(String filename, String username) {
       if (fileRepository.findByFilenameAndUsername(filename, username).isEmpty()) {
           throw new ErrorInputData("Error delete file");
       } else {
           fileRepository.deleteByFilenameAndUsername(filename, username);
       }
    }

    public void editFilename(String oldFilename, String newFilename, String username) {
        if (fileRepository.findByFilenameAndUsername(newFilename, username).isEmpty()) {
            if (fileRepository.findByFilenameAndUsername(oldFilename, username).isEmpty()) {
                throw new ErrorInputData("No such file presented");
            } else {
                FileMyDB fileDB = fileRepository.findByFilenameAndUsername(oldFilename, username).get(0);
                fileDB.setFilename(newFilename);
                fileRepository.save(fileDB);
            }
        } else {
            throw new ErrorInputData("Such file already uploaded");
        }

    }
}
