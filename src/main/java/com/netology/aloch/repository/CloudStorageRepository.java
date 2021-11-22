package com.netology.aloch.repository;

import com.netology.aloch.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.file.Files;
import java.util.List;

@Repository
public class CloudStorageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List getFilesList() {
        Query query = entityManager.createQuery("select f.filename from FILES f", Files.class);
        return query.getResultList();
    }

    public List getUser(String name) {
        Query query = entityManager.createQuery("select u from USERS u where u.username = :username", User.class);
        query.setParameter("username", name);
        return query.getResultList();

    }
}
