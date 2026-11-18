package comunicacao.centro.usefinanceiro.api.service;

import comunicacao.centro.usefinanceiro.api.model.Doacao;
import comunicacao.centro.usefinanceiro.api.dto.NovaDoacao;
import comunicacao.centro.usefinanceiro.api.repository.DoacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DoacaoService {
    private final DoacaoRepository repository;
    private final CentroClient centros;

    public DoacaoService(DoacaoRepository repository, CentroClient centros) {
        this.repository = repository;
        this.centros = centros;
    }

    public Doacao cadastrar(NovaDoacao nova) {
        centros.validarCentro(nova.centroId());
        String origem = StringUtils.hasText(nova.origem()) ? nova.origem().strip() : null;
        var doacao = new Doacao(nova.centroId(), origem,
                nova.valor(), nova.dataRecebimento());
        return repository.save(doacao);
    }

    public List<Doacao> listarPorCentro(String centroId) {
        return repository.findByCentroIdOrderByDataRecebimentoDescIdAsc(centroId);
    }
}
