package comunicacao.centro.usefinanceiro.repository;

import comunicacao.centro.usefinanceiro.api.Conta;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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
                .query((row, index) -> new Conta(
                        row.getString("id"),
                        row.getString("centro_id"),
                        row.getString("descricao"),
                        row.getString("categoria"),
                        row.getBigDecimal("valor"),
                        row.getObject("vencimento", LocalDate.class),
                        row.getString("status")))
                .list();
    }
}
