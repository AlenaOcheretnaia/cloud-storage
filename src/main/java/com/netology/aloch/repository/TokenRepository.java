package com.netology.aloch.repository;

import com.netology.aloch.model.UserToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Qualifier("tokens")
@Repository
public interface TokenRepository extends JpaRepository<UserToken, Integer> {

    Optional<UserToken> findByToken(String token);

    @Transactional
    void deleteByToken(String token);
}
