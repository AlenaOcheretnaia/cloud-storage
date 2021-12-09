package com.netology.aloch.repository;

import com.netology.aloch.model.UserMyDB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("user")
@Repository
public interface UserRepository extends JpaRepository<UserMyDB, String> {

    Optional<UserMyDB> findByLoginAndPassword(String login, String password);

    Optional<UserMyDB> findById(String login);

}
