package com.netology.aloch.controller;

import com.netology.aloch.repository.CloudStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudStorageController {

    @Autowired
    CloudStorageRepository cloudStorageRepository;

    @GetMapping("/")
    public String hello(){
        return "Hello from Cloud Storage by AlOch";
    }

    @GetMapping("/user")
        public String getFiles(@RequestParam String username) {
        var result = cloudStorageRepository.getUser(username);
        return ("User is:" + "<br>" + result);
    }


}
