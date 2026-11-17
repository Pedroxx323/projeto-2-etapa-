package comunicacao.centro.usefinanceiro.service;

import comunicacao.centro.usefinanceiro.api.Conta;
import comunicacao.centro.usefinanceiro.api.NovaConta;
import comunicacao.centro.usefinanceiro.api.NovoPagamento;
import comunicacao.centro.usefinanceiro.api.Pagamento;
import comunicacao.centro.usefinanceiro.repository.ContaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public Pagamento registrarPagamento(String contaId, NovoPagamento novo) {
        Conta conta = repository.buscarParaBaixa(contaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada."));
        if ("PAGA".equals(conta.status())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A conta já está paga.");
        }

        var pagamento = new Pagamento(conta.id(), novo.dataPagamento(),
                novo.formaPagamento().strip(), conta.valor());
        repository.registrarBaixa(pagamento);
        return pagamento;
    }

    public List<Conta> listarPorCentro(String centroId) {
        return repository.listarPorCentro(centroId);
    }
}
