package com.example.desafiocnab.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum TipoTransacao {

    DEBITO(1L, "Débito", "in"),
    BOLETO(2L, "Boleto", "out"),
    FINANCIAMENTO(3L, "Financiamento", "out"),
    CREDITO(4L, "Crédito", "in"),
    RECEBIMENTO_EMPRESTIMO(5L, "Recebimento Empréstimo", "in"),
    VENDAS(6L, "Vendas", "in"),
    RECEBIMENTO_TED(7L, "Recebimento Ted", "in"),
    RECEBIMENTO_DOC(8L, "Recebimento Doc", "in"),
    ALUGUEL(9L, "Aluguel", "out");

    private Long type;
    private String description;
    private String Nature;

    TipoTransacao(Long type, String description, String nature) {
        this.type = type;
        this.description = description;
        Nature = nature;
    }

    public static TipoTransacao getByType(Long type) {
        for (TipoTransacao transacao : TipoTransacao.values()) {
            if (Objects.equals(transacao.getType(), type)) {
                return transacao;
            }
        }

        return null;
    }
}
