package dev.semijoias.silvanasemijoias.Vendedora;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedoras")
public class VendedoraController {

    private final VendedoraService vendedoraService;

    public VendedoraController(VendedoraService vendedoraService) {
        this.vendedoraService = vendedoraService;
    }

    @GetMapping
    public ResponseEntity<List<VendedoraDTO>> listarVendedoras() {
        List<VendedoraDTO> vendedoras = vendedoraService.listarVendedoras();
        return ResponseEntity.ok(vendedoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedoraDTO> buscarPorId(@PathVariable Long id) {
        VendedoraDTO vendedora = vendedoraService.buscarPorId(id);
        if (vendedora != null) {
            return ResponseEntity.ok(vendedora);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<VendedoraDTO> criarVendedora(@RequestBody @Valid VendedoraDTO vendedoraDTO) {
        VendedoraDTO vendedoraCriado = vendedoraService.cadastrarVendedora(vendedoraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedoraCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedoraDTO> atualizarVendedora(@PathVariable Long id, @RequestBody @Valid VendedoraDTO vendedoraDTO) {
        VendedoraDTO vendedoraAtualizado = vendedoraService.atualizarVendedora(id, vendedoraDTO);
        if (vendedoraAtualizado != null) {
            return ResponseEntity.ok(vendedoraAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerVendedora(@PathVariable Long id) {
        if (vendedoraService.buscarPorId(id) != null) {
            vendedoraService.removerVendedora(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedora não encontrado");
        }
    }

}
