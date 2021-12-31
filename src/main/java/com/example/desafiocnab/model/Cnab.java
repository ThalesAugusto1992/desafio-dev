package com.example.desafiocnab.model;

import com.example.desafiocnab.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cnab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TipoTransacao tipoTransacao;

    private Date data;

    private Double valor;

    private String cpf;

    private String cartao;

    private String horaTranferencia;

    private String donoLoja;

    private String nomeLoja;
}
