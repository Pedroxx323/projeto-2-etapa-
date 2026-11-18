package comunicacao.centro.usefinanceiro.api.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovaDoacao(
        @NotBlank @Size(max = 50) String centroId,
        @Size(max = 150) String origem,
        @NotNull @DecimalMin("0.01") @Digits(integer = 10, fraction = 2) BigDecimal valor,
        @NotNull @PastOrPresent LocalDate dataRecebimento
) {}
