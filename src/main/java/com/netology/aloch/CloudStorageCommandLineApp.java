package com.netology.aloch;

import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.model.UserFiles;
import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CloudStorageCommandLineApp implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

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
                    .name("filename" + (i + 1))
                    .type("text/plain")
                    .data(new byte[]{(byte) (i * 100)})
                    .build();
            entityManager.persist(file);

            var content = UserFiles.builder()
                    .userName(users.get(i))
                    .fileId(i + 1)
                    .build();
            entityManager.persist(content);

        }

    }

}