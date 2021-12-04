package com.netology.aloch;

import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.model.UserMyDB;
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

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var emails = List.of("user1@gmail.com", "user2@gmail.com", "user3@gmail.com", "user4@gmail.com", "user5@gmail.com");
        var passwords = List.of("pass1", "pass2", "pass3", "pass4", "pass5");

        for (int i = 0; i < emails.size(); i++) {
            var user = UserMyDB.builder()
                    .login(emails.get(i))
                    .password(passwords.get(i))
                    .build();
            entityManager.persist(user);
        }

        for (int i = 0; i < 5; i++) {
            var file = FileMyDB.builder()
                    .name("filename"+i)
                    .type("text/plain")
                    .data(new byte[] {(byte) (i*100)})
                    .build();
            entityManager.persist(file);
        }

    }

}