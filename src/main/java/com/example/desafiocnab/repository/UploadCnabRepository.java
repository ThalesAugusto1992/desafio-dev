package com.example.desafiocnab.repository;

import com.example.desafiocnab.model.UploadedCnab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadCnabRepository extends JpaRepository<UploadedCnab, Long> {

    List<UploadedCnab> findAllByProcessed(Boolean processed);
}
