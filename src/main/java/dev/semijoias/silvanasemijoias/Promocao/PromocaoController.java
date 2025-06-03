package dev.semijoias.silvanasemijoias.Promocao;

import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("promocoes")
@RequiredArgsConstructor
public class PromocaoController {

    private final PromocaoService promocaoService;

    @PostMapping
    public ResponseEntity<PromocaoDTO> createPromocao(@RequestBody @Valid PromocaoDTO dto) {
        return ResponseEntity.ok(promocaoService.createPromocao(dto));
    }

    @GetMapping
    public List<PromocaoDTO> listarPromocoes() {
        return promocaoService.listarPromocoes();
    }

    @GetMapping("/{id}")
    public PromocaoDTO buscarPorId(@PathVariable Long id) {
        return promocaoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PromocaoDTO atualizarPromocao(@PathVariable Long id, @RequestBody @Valid PromocaoDTO dto) {
        return promocaoService.atualizarPromocao(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPromocao(@PathVariable Long id) {
        promocaoService.deletarPromocao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{promocaoId}/joias/{joiaId}")
    public PromocaoDTO adicionarJoia(@PathVariable Long promocaoId, @PathVariable Long joiaId) {
        return promocaoService.adicionarJoiaEmPromocao(promocaoId, joiaId);
    }

    @DeleteMapping("/{promocaoId}/joias/{joiaId}")
    public PromocaoDTO removerJoia(@PathVariable Long promocaoId, @PathVariable Long joiaId) {
        return promocaoService.removerJoiaDaPromocao(promocaoId, joiaId);
    }
}
