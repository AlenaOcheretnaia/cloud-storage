package com.netology.aloch.repository;

import com.netology.aloch.model.FileMyDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileDBRepository extends JpaRepository<FileMyDB, String> {

}
