package comunicacao.centro.usefinanceiro.api;

import comunicacao.centro.usefinanceiro.service.ContaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta cadastrar(@Valid @RequestBody NovaConta nova) {
        return service.cadastrar(nova);
    }

    @PostMapping("/{contaId}/pagamento")
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento registrarPagamento(
            @PathVariable("contaId") @Size(max = 36) String contaId,
            @Valid @RequestBody NovoPagamento novo) {
        return service.registrarPagamento(contaId, novo);
    }

    @GetMapping
    public List<Conta> listar(@RequestParam("centroId") @NotBlank @Size(max = 50) String centroId) {
        return service.listarPorCentro(centroId);
    }
}
