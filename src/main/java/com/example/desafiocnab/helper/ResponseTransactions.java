package com.example.desafiocnab.helper;

import com.example.desafiocnab.model.Cnab;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransactions {

    private List<Cnab> transacoes;

    private Double saltoTotal;
}
