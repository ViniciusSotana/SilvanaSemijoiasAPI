package dev.semijoias.silvanasemijoias.Colecao;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("colecoes")
@CrossOrigin
public class ColecaoController {

    private final ColecaoService colecaoService;

    public ColecaoController(ColecaoService colecaoService) {
        this.colecaoService = colecaoService;
    }

    @GetMapping
    public ResponseEntity<List<ColecaoResponseDTO>> listarColecao() {
        List<ColecaoResponseDTO> colecoes = colecaoService.listarColecoes();
        return ResponseEntity.ok(colecoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColecaoRequestDTO> buscarPorId(@PathVariable Long id) {
        ColecaoRequestDTO colecao = colecaoService.buscarPorId(id);
        if (colecao != null) {
            return ResponseEntity.ok(colecao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ColecaoResponseDTO> criarColecao(@RequestBody @Valid ColecaoRequestDTO ColecaoDTO) {
        ColecaoResponseDTO colecaoCriada = colecaoService.cadastrarColecao(ColecaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(colecaoCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColecaoRequestDTO> atualizarColecao(@PathVariable Long id, @RequestBody @Valid ColecaoRequestDTO ColecaoDTO) {
        ColecaoRequestDTO colecaoAtualizada = colecaoService.atualizarColecao(id, ColecaoDTO);
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
