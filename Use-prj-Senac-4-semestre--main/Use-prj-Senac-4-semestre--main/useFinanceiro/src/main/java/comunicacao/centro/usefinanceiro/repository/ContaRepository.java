package comunicacao.centro.usefinanceiro.repository;

import comunicacao.centro.usefinanceiro.api.Conta;
import comunicacao.centro.usefinanceiro.api.Pagamento;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ContaRepository {
    private final JdbcClient jdbc;

    public ContaRepository(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    public void inserir(Conta conta) {
        jdbc.sql("""
                INSERT INTO conta (id, centro_id, descricao, categoria, valor, vencimento, status)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """)
                .params(conta.id(), conta.centroId(), conta.descricao(), conta.categoria(),
                        conta.valor(), conta.vencimento(), conta.status())
                .update();
    }

    public List<Conta> listarPorCentro(String centroId) {
        return jdbc.sql("""
                SELECT id, centro_id, descricao, categoria, valor, vencimento, status
                FROM conta
                WHERE centro_id = ?
                ORDER BY vencimento, id
                """)
                .param(centroId)
                .query(ContaRepository::mapearConta)
                .list();
    }

    public Optional<Conta> buscarParaBaixa(String contaId) {
        return jdbc.sql("""
                SELECT id, centro_id, descricao, categoria, valor, vencimento, status
                FROM conta
                WHERE id = ?
                FOR UPDATE
                """)
                .param(contaId)
                .query(ContaRepository::mapearConta)
                .optional();
    }

    public void registrarBaixa(Pagamento pagamento) {
        jdbc.sql("""
                INSERT INTO pagamento_conta (conta_id, data_pagamento, forma_pagamento, valor)
                VALUES (?, ?, ?, ?)
                """)
                .params(pagamento.contaId(), pagamento.dataPagamento(),
                        pagamento.formaPagamento(), pagamento.valor())
                .update();
        jdbc.sql("UPDATE conta SET status = 'PAGA' WHERE id = ?")
                .param(pagamento.contaId())
                .update();
    }

    private static Conta mapearConta(ResultSet row, int index) throws SQLException {
        return new Conta(
                row.getString("id"),
                row.getString("centro_id"),
                row.getString("descricao"),
                row.getString("categoria"),
                row.getBigDecimal("valor"),
                row.getObject("vencimento", LocalDate.class),
                row.getString("status"));
    }
}
