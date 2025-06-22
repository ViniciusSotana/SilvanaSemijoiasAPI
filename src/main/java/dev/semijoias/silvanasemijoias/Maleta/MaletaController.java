package dev.semijoias.silvanasemijoias.Maleta;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("maletas")
@CrossOrigin
public class MaletaController {

    private final MaletaService maletaService;
    
    public MaletaController(MaletaService maletaService) {
        this.maletaService = maletaService;
    }

    @GetMapping
    public ResponseEntity<List<MaletaDTO>> listarMaletas() {
        List<MaletaDTO> maletas = maletaService.listarMaletas();
        return ResponseEntity.ok(maletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaletaDTO> buscarPorId(@PathVariable Long id) {
        MaletaDTO maleta = maletaService.buscarPorId(id);
        if (maleta != null) {
            return ResponseEntity.ok(maleta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @PostMapping
    public ResponseEntity<MaletaDTO> criarMaleta(@RequestBody @Valid MaletaDTO MaletaDTO) {
        MaletaDTO maletaCriada = maletaService.cadastrarMaleta(MaletaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(maletaCriada);
    }
    */

    @PostMapping
    public ResponseEntity<MaletaResponseDTO> criarMaleta(@RequestBody @Valid MaletaRequestDTO maletaRequestDTO) {
        MaletaResponseDTO maleta = maletaService.cadastrarMaleta(maletaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(maleta);
    }


    /*@PutMapping("/{id}")
    public ResponseEntity<MaletaDTO> atualizarMaleta(@PathVariable Long id, @RequestBody @Valid MaletaDTO maletaDTO) {
        MaletaDTO maletaAtualizada = maletaService.atualizarMaleta(id, maletaDTO);
        if (maletaAtualizada != null) {
            return ResponseEntity.ok(maletaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<MaletaDTO> atualizarMaleta(@PathVariable Long id, @RequestBody @Valid MaletaVendedoraDTO maletaVendedoraDTO) {
        MaletaDTO maletaAtualizada = maletaService.atualizarMaleta(id, maletaVendedoraDTO);
        if (maletaAtualizada != null) {
            return ResponseEntity.ok(maletaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerMaleta(@PathVariable Long id) {
        if (maletaService.buscarPorId(id) != null) {
            maletaService.removerMaleta(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maleta não encontrado");
        }
    }
    
}
