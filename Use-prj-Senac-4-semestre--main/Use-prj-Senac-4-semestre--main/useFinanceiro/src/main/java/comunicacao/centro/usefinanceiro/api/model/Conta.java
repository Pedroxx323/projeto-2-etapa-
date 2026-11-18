package comunicacao.centro.usefinanceiro.api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "centro_id", length = 50, nullable = false)
    private String centroId;

    @Column(length = 150, nullable = false)
    private String descricao;

    @Column(length = 50, nullable = false)
    private String categoria;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate vencimento;

    @Column(length = 20, nullable = false)
    private String status;

    protected Conta() {}

    public Conta(String centroId,
            String descricao,
            String categoria,
            BigDecimal valor,
            LocalDate vencimento,
            String status) {
        this.centroId = centroId;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.vencimento = vencimento;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCentroId() {
        return centroId;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void marcarComoPaga() {
        status = "PAGA";
    }
}
