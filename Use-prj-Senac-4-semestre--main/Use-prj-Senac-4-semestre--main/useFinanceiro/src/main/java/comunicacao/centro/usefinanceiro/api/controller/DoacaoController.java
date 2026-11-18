package comunicacao.centro.usefinanceiro.api.controller;

import comunicacao.centro.usefinanceiro.api.model.Doacao;
import comunicacao.centro.usefinanceiro.api.dto.NovaDoacao;
import comunicacao.centro.usefinanceiro.api.service.DoacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {
    private final DoacaoService service;

    public DoacaoController(DoacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doacao cadastrar(@Valid @RequestBody NovaDoacao nova) {
        return service.cadastrar(nova);
    }

    @GetMapping
    public List<Doacao> listar(@RequestParam("centroId") @NotBlank @Size(max = 50) String centroId) {
        return service.listarPorCentro(centroId);
    }
}
