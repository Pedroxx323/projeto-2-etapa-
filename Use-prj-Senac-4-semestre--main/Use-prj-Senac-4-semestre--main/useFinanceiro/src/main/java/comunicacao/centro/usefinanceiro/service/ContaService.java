package comunicacao.centro.usefinanceiro.service;

import comunicacao.centro.usefinanceiro.api.Conta;
import comunicacao.centro.usefinanceiro.api.NovaConta;
import comunicacao.centro.usefinanceiro.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContaService {
    private final ContaRepository repository;
    private final CentroClient centros;

    public ContaService(ContaRepository repository, CentroClient centros) {
        this.repository = repository;
        this.centros = centros;
    }

    public Conta cadastrar(NovaConta nova) {
        centros.validarCentro(nova.centroId());
        var conta = new Conta(UUID.randomUUID().toString(), nova.centroId(), nova.descricao(),
                nova.categoria(), nova.valor(), nova.vencimento(), "PENDENTE");
        repository.inserir(conta);
        return conta;
    }

    public List<Conta> listarPorCentro(String centroId) {
        return repository.listarPorCentro(centroId);
    }
}
