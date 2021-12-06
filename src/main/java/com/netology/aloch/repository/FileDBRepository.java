package com.netology.aloch.repository;

import com.netology.aloch.model.FileMyDB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("files")
@Repository
public interface FileDBRepository extends JpaRepository<FileMyDB, Integer> {

}
