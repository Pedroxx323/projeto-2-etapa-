package comunicacao.centro.usefinanceiro.api.service;

import comunicacao.centro.usefinanceiro.api.model.Conta;
import comunicacao.centro.usefinanceiro.api.dto.NovaConta;
import comunicacao.centro.usefinanceiro.api.dto.NovoPagamento;
import comunicacao.centro.usefinanceiro.api.model.Pagamento;
import comunicacao.centro.usefinanceiro.api.repository.ContaRepository;
import comunicacao.centro.usefinanceiro.api.repository.PagamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContaService {
    private final ContaRepository repository;
    private final PagamentoRepository pagamentos;
    private final CentroClient centros;

    public ContaService(ContaRepository repository, PagamentoRepository pagamentos, CentroClient centros) {
        this.repository = repository;
        this.pagamentos = pagamentos;
        this.centros = centros;
    }

    public Conta cadastrar(NovaConta nova) {
        centros.validarCentro(nova.centroId());
        var conta = new Conta(nova.centroId(), nova.descricao(),
                nova.categoria(), nova.valor(), nova.vencimento(), "PENDENTE");
        return repository.save(conta);
    }

    @Transactional
    public Pagamento registrarPagamento(String contaId, NovoPagamento novo) {
        Conta conta = repository.findContaById(contaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada."));
        if ("PAGA".equals(conta.getStatus())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A conta já está paga.");
        }

        var pagamento = new Pagamento(conta.getId(), novo.dataPagamento(),
                novo.formaPagamento().strip(), conta.getValor());
        conta.marcarComoPaga();
        return pagamentos.save(pagamento);
    }

    public List<Conta> listarPorCentro(String centroId) {
        return repository.findByCentroIdOrderByVencimentoAscIdAsc(centroId);
    }
}
