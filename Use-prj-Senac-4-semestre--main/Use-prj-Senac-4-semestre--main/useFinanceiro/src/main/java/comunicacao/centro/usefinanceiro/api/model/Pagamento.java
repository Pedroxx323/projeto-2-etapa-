package comunicacao.centro.usefinanceiro.api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento_conta")
public class Pagamento {
    @Id
    @Column(name = "conta_id", columnDefinition = "CHAR(36)")
    private String contaId;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Column(name = "forma_pagamento", length = 50, nullable = false)
    private String formaPagamento;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal valor;

    protected Pagamento() {}

    public Pagamento(String contaId, LocalDate dataPagamento, String formaPagamento, BigDecimal valor) {
        this.contaId = contaId;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
    }

    public String getContaId() {
        return contaId;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
