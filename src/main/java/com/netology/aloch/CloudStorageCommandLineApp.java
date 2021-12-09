package com.netology.aloch;

import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.repository.FileRepository;
import com.netology.aloch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CloudStorageCommandLineApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var users = List.of("user1", "user2", "user3", "user4", "user5");
        var passwords = List.of("pass1", "pass2", "pass3", "pass4", "pass5");

        for (int i = 0; i < users.size(); i++) {
            var user = UserMyDB.builder()
                    .login(users.get(i))
                    .password(passwords.get(i))
                    .build();
            userRepository.save(user);
        }

        for (int i = 0; i < 5; i++) {
            var file = FileMyDB.builder()
                    .filename("filename" + (i + 1) + ".txt")
                    .type("text/plain")
                    .data(new byte[]{(byte) (i * 10000)})
                    .username(users.get(i))
                    .build();
            fileRepository.save(file);
        }

    }

}