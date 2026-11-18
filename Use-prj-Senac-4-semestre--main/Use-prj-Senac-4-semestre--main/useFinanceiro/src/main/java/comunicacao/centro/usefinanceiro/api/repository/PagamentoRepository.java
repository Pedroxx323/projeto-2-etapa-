package comunicacao.centro.usefinanceiro.api.repository;

import comunicacao.centro.usefinanceiro.api.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, String> {
}
