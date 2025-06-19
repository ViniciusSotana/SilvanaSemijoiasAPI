package dev.semijoias.silvanasemijoias.TipoJoia;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoJoias")
@CrossOrigin
public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoDTO>> listarTipos() {
        List<TipoDTO> tipos = tipoService.listarTipos();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> buscarPorId(@PathVariable Long id) {
        TipoDTO tipo = tipoService.buscarPorId(id);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoDTO> criarTipo(@RequestBody @Valid TipoDTO tipoDTO) {
        TipoDTO tipoCriado = tipoService.cadastrarTipo(tipoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDTO> atualizarTipo(@PathVariable Long id, @RequestBody @Valid TipoDTO tipoDTO) {
        TipoDTO tipoAtualizado = tipoService.atualizarTipo(id, tipoDTO);
        if (tipoAtualizado != null) {
            return ResponseEntity.ok(tipoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerTipo(@PathVariable Long id) {
        if (tipoService.buscarPorId(id) != null) {
            tipoService.removerTipo(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo não encontrado");
        }
    }


}
