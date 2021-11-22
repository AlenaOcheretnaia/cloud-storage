package com.netology.aloch;

import com.netology.aloch.entity.File;
import com.netology.aloch.entity.User;
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
        var names = List.of("user1", "user2", "user3", "user4", "user5");
        var passwords = List.of("pass1", "pass2", "pass3", "pass4", "pass5");

        for (int i = 0; i < names.size(); i++) {
            var user = User.builder()
                    .username(names.get(i))
                    .password(passwords.get(i))
                    .build();
            entityManager.persist(user);
        }

        for (int i = 0; i < 5; i++) {
            var file = File.builder()
                    .filename("filename"+i)
                    .data(new byte[] {(byte) i})
                    .build();
            entityManager.persist(file);
        }

    }

}