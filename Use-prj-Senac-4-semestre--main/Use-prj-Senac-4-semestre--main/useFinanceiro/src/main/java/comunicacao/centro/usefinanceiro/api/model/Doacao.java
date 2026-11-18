package comunicacao.centro.usefinanceiro.api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "doacao")
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "centro_id", length = 50, nullable = false)
    private String centroId;

    @Column(length = 150)
    private String origem;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "data_recebimento", nullable = false)
    private LocalDate dataRecebimento;

    protected Doacao() {}

    public Doacao(String centroId, String origem, BigDecimal valor, LocalDate dataRecebimento) {
        this.centroId = centroId;
        this.origem = origem;
        this.valor = valor;
        this.dataRecebimento = dataRecebimento;
    }

    public String getId() {
        return id;
    }

    public String getCentroId() {
        return centroId;
    }

    public String getOrigem() {
        return origem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }
}
