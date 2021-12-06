package com.netology.aloch.repository;

import com.netology.aloch.model.UserMyDB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("user")
@Repository
public interface UserRepository extends JpaRepository<UserMyDB, String> {

    Optional<UserMyDB> findByLoginAndPassword(String login, String password);

    Optional<UserMyDB> findById(String login);

    Optional<UserMyDB> findByToken(String token);

//    @Modifying
//    @Query("update users u set u.token = :token where u.login = :login")
//    void updateToken(@Param(value = "login") String login, @Param(value="token") String token);
//
//    public void updateUserToken(String login, String token) {
//        updateToken(login, token);
//    }

//    void updateToken(String login, String token) {
//        UserMyDB user = findByLogin(login).get();
//        user.setToken(token);
//        UserRepository.save(user);
//    }
}
