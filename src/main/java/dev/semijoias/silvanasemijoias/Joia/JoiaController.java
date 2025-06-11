package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.relatorios.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("joias")
public class JoiaController {

    private final JoiaService joiaService;
    private final RelatorioService relatorioService;

    public JoiaController(JoiaService joiaService, RelatorioService relatorioService) {
        this.joiaService = joiaService;
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public ResponseEntity<List<JoiaDTO>> listarJoias() {
        List<JoiaDTO> joias = joiaService.listarJoia();
        return ResponseEntity.ok(joias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoiaDTO> buscarPorId(@PathVariable Long id) {
        JoiaDTO joia = joiaService.buscarPorId(id);
        if (joia != null) {
            return ResponseEntity.ok(joia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/relatorios")
    public ResponseEntity<byte[]> buscarJoiasEsgotadas(){
        return this.relatorioService.gerarRelatorioDeJoiasEsgotadas();
    }

    @GetMapping("/relatoriosTipo")
    public ResponseEntity<byte[]> gerarRelatorioDeJoiasPorTipo(@RequestParam String descricao) {
        return this.relatorioService.gerarRelatorioDeJoiasPorTipo(descricao);
    }

    @PostMapping
    public ResponseEntity<JoiaDTO> criarJoia(@RequestBody @Valid JoiaDTO JoiaDTO) {
        JoiaDTO joiaCriada = joiaService.cadastrarJoia(JoiaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(joiaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JoiaDTO> atualizarJoia(@PathVariable Long id, @RequestBody @Valid JoiaDTO JoiaDTO) {
        JoiaDTO joiaAtualizada = joiaService.atualizarJoia(id, JoiaDTO);
        if (joiaAtualizada != null) {
            return ResponseEntity.ok(joiaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerJoia(@PathVariable Long id) {
        if (joiaService.buscarPorId(id) != null) {
            joiaService.removerJoia(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Joia não encontrado");
        }
    }
    
}
