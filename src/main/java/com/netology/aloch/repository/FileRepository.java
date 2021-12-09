package com.netology.aloch.repository;

import com.netology.aloch.model.FileMyDB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Qualifier("files")
@Repository
public interface FileRepository extends JpaRepository<FileMyDB, Integer> {

    List<FileMyDB> findByUsername(String userName);

    List<FileMyDB> findByFilenameAndUsername(String filename, String username);

    @Transactional
    void deleteByFilenameAndUsername(String filename, String username);
}
