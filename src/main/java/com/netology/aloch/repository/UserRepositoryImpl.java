//package com.netology.aloch.repository;
//
//import com.netology.aloch.model.UserMyDB;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//
//@Repository
//public class UserRepositoryImpl implements UserRepository{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public boolean checkUserDB(String login, String pwd) {
//        List<UserMyDB> result = entityManager
//                .createQuery("select u from users u where u.login = :login and u.password = :password", UserMyDB.class)
//                .setParameter("login", login)
//                .setParameter("password", pwd)
//                .getResultList();
//
//        if (result.size() != 0) {
//            return true;
//        } else
//            return false;
//    }
//
//
////    public void assignTokenToUser(String userName, String token) {
////
////    }
//

//}
