package comunicacao.centro.usefinanceiro.api.repository;

import comunicacao.centro.usefinanceiro.api.model.Conta;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, String> {
    List<Conta> findByCentroIdOrderByVencimentoAscIdAsc(String centroId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Conta> findContaById(String id);
}
