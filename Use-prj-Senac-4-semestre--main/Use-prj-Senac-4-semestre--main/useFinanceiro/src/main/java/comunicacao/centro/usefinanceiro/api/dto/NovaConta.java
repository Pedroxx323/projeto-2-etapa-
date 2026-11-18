package comunicacao.centro.usefinanceiro.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovaConta(
        @NotBlank @Size(max = 50) String centroId,
        @NotBlank @Size(max = 150) String descricao,
        @NotBlank @Size(max = 50) String categoria,
        @NotNull @DecimalMin("0.01") @Digits(integer = 10, fraction = 2) BigDecimal valor,
        @NotNull LocalDate vencimento
) {}
