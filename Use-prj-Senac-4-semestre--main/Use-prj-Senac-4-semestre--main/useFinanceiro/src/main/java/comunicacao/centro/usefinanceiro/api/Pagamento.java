package comunicacao.centro.usefinanceiro.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Pagamento(
        String contaId,
        LocalDate dataPagamento,
        String formaPagamento,
        BigDecimal valor
) {}
