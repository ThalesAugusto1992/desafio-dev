package com.example.desafiocnab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UploadedCnab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private String fileName;

    private Boolean processed;

    public UploadedCnab(String content, String fileName, Boolean processed) {
        this.content = content;
        this.fileName = fileName;
        this.processed = processed;
    }
}
