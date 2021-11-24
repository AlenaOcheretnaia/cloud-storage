package com.netology.aloch.repository;

import com.netology.aloch.entity.FileMyDB;
import com.netology.aloch.entity.UserMyDB;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CloudStorageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<FileMyDB> getFilesList() {
        Query query = entityManager.createQuery("select f from files f", FileMyDB.class);
        return query.getResultList();
    }

    public List getFileIdByName(String fileName) {
        Query query = entityManager.createQuery("select f.id from files f where f.name = :fileName", UserMyDB.class);
        query.setParameter("fileName", fileName);
        return query.getResultList();
    }

}
