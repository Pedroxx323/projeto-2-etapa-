package comunicacao.centro.usefinanceiro.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NovoPagamento(
        @NotNull @PastOrPresent LocalDate dataPagamento,
        @NotBlank @Size(max = 50) String formaPagamento
) {}
