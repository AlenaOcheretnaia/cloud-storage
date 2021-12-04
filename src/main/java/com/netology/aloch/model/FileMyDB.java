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
    @Column(nullable = false)
    private String name;
    
    private String type;

    @Lob
    private byte[] data;

    public String getId() {
        return name;
    }


}
