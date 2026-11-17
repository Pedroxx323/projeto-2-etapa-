package comunicacao.centro.usefinanceiro.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Conta(
        String id,
        String centroId,
        String descricao,
        String categoria,
        BigDecimal valor,
        LocalDate vencimento,
        String status
) {}
