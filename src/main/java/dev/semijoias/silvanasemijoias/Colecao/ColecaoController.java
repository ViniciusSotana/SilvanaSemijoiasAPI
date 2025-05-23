package dev.semijoias.silvanasemijoias.Colecao;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("colecoes")
public class ColecaoController {

    private final ColecaoService colecaoService;

    public ColecaoController(ColecaoService colecaoService) {
        this.colecaoService = colecaoService;
    }

    @GetMapping
    public ResponseEntity<List<ColecaoDTO>> listarColecao() {
        List<ColecaoDTO> colecoes = colecaoService.listarColecoes();
        return ResponseEntity.ok(colecoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColecaoDTO> buscarPorId(@PathVariable Long id) {
        ColecaoDTO colecao = colecaoService.buscarPorId(id);
        if (colecao != null) {
            return ResponseEntity.ok(colecao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ColecaoDTO> criarColecao(@RequestBody @Valid ColecaoDTO ColecaoDTO) {
        ColecaoDTO colecaoCriada = colecaoService.cadastrarColecao(ColecaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(colecaoCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColecaoDTO> atualizarColecao(@PathVariable Long id, @RequestBody @Valid ColecaoDTO ColecaoDTO) {
        ColecaoDTO colecaoAtualizada = colecaoService.atualizarColecao(id, ColecaoDTO);
        if (colecaoAtualizada != null) {
            return ResponseEntity.ok(colecaoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerColecao(@PathVariable Long id) {
        if (colecaoService.buscarPorId(id) != null) {
            colecaoService.removerColecao(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coleção não encontrado");
        }
    }
    
}
