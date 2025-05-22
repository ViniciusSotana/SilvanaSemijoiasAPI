package dev.semijoias.silvanasemijoias.Imagem;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @GetMapping
    public ResponseEntity<List<ImagemDTO>> listarImagens() {
        List<ImagemDTO> imagens = imagemService.listarImagens();
        return ResponseEntity.ok(imagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagemDTO> buscarPorId(@PathVariable Long id) {
        ImagemDTO imagem = imagemService.buscarPorId(id);
        if (imagem != null) {
            return ResponseEntity.ok(imagem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ImagemDTO> criarImagem(@RequestBody @Valid ImagemDTO imagemDTO) {
        ImagemDTO imagemCriada = imagemService.cadastrarImagem(imagemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(imagemCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagemDTO> atualizarImagem(@PathVariable Long id, @RequestBody @Valid ImagemDTO imagemDTO) {
        ImagemDTO imagemAtualizada = imagemService.atualizarImagem(id, imagemDTO);
        if (imagemAtualizada != null) {
            return ResponseEntity.ok(imagemAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerImagem(@PathVariable Long id) {
        if (imagemService.buscarPorId(id) != null) {
            imagemService.removerImagem(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrado");
        }
    }


}
