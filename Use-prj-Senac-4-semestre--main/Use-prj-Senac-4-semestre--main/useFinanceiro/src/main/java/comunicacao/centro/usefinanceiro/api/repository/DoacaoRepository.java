package comunicacao.centro.usefinanceiro.api.repository;

import comunicacao.centro.usefinanceiro.api.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, String> {
    List<Doacao> findByCentroIdOrderByDataRecebimentoDescIdAsc(String centroId);
}
