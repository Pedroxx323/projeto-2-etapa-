package comunicacao.centro.useService.api.controller;

import comunicacao.centro.useService.api.model.Centro;
import comunicacao.centro.useService.api.service.CentroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centros")
public class CentroController {

    private final CentroService centroService;

    public CentroController(CentroService centroService) {
        this.centroService = centroService;
    }

    @GetMapping
    public List<Centro> listarCentros() {
        return centroService.listarCentros();
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCentro(@RequestBody Centro centro) {

        try {
            Centro salvo = centroService.cadastrarCentro(centro);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(salvo);

        } catch (IllegalArgumentException erro) {
            return ResponseEntity
                    .badRequest()
                    .body(erro.getMessage());
        }
    }
}