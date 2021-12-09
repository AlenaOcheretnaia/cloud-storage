package com.netology.aloch.model;

import lombok.*;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "files")
public class FileMyDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String filename;

    private String type;

    @Lob
    private byte[] data;

    private String username;

    public FileMyDB(String fileName, String type, byte[] data, String username) {
        this.filename = fileName;
        this.type = type;
        this.data = data;
        this.username = username;
    }
}
