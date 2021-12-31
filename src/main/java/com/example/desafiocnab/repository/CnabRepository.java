package com.example.desafiocnab.repository;

import com.example.desafiocnab.model.Cnab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CnabRepository extends JpaRepository<Cnab, Long> {

    List<Cnab> findAllByNomeLoja(String loja);
}
