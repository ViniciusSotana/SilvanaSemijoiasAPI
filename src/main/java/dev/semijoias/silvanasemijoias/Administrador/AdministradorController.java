package dev.semijoias.silvanasemijoias.Administrador;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("administradores")
@CrossOrigin
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> listarAdministradores() {
        List<AdministradorDTO> adms = administradorService.listarAdministrador();
        return ResponseEntity.ok(adms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> buscarPorId(@PathVariable Long id) {
        AdministradorDTO adm = administradorService.buscarPorId(id);
        if (adm != null) {
            return ResponseEntity.ok(adm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AdministradorDTO> criarAdministrador(@RequestBody @Valid AdministradorDTO administradorDTO) {
        AdministradorDTO admCriado = administradorService.cadastrarAdministrador(administradorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(admCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> atualizarAdministrador(@PathVariable Long id, @RequestBody @Valid AdministradorDTO administradorDTO) {
        AdministradorDTO admAtualizado = administradorService.atualizarAdministrador(id, administradorDTO);
        if (admAtualizado != null) {
            return ResponseEntity.ok(admAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerAdministrador(@PathVariable Long id) {
        if (administradorService.buscarPorId(id) != null) {
            administradorService.removerAdministrador(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ADM não encontrado");
        }
    }

}
