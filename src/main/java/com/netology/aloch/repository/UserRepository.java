package com.netology.aloch.repository;

import com.netology.aloch.model.UserMyDB;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean checkUserDB(String login, String pwd) {
        List<UserMyDB> result = entityManager
                .createQuery("select u from users u where u.login = :login and u.password = :password", UserMyDB.class)
                .setParameter("login", login)
                .setParameter("password", pwd)
                .getResultList();

        if (result.size() != 0) {
            return true;
        } else
            return false;
    }


}
