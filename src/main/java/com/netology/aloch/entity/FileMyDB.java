package com.netology.aloch.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "files")
public class FileMyDB {

//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;

    @Id
    @Column(nullable = false)
    private String name;
    
    private String type;

    @Lob
    private byte[] data;

    public String getId() {
        return name;
    }


}
