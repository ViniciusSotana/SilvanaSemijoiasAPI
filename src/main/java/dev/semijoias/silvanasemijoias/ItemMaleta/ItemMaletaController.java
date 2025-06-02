package dev.semijoias.silvanasemijoias.ItemMaleta;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("itensMaleta")
public class ItemMaletaController {

    private final ItemMaletaService itemMaletaService;

    public ItemMaletaController(ItemMaletaService itemMaletaService) {
        this.itemMaletaService = itemMaletaService;
    }

    @GetMapping
    public ResponseEntity<List<ItemMaletaDTO>> listarTodos() {
        List<ItemMaletaDTO> itens = itemMaletaService.listarItemMaleta();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("maletas/{maletaId}")
    public ResponseEntity<List<ItemMaletaDTO>> listarPorMaleta(@PathVariable Long maletaId) {
        List<ItemMaletaDTO> itens = itemMaletaService.listarPorMaleta(maletaId);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMaletaDTO> buscarPorId(@PathVariable Long id) {
        ItemMaletaDTO item = itemMaletaService.buscarPorId(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<ItemMaletaDTO> cadastrar(@Valid @RequestBody ItemMaletaDTO dto) {
        ItemMaletaDTO salvo = itemMaletaService.cadastrarItemMaleta(dto);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemMaletaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ItemMaletaDTO dto) {
        ItemMaletaDTO atualizado = itemMaletaService.atualizarItemMaleta(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemMaletaService.removerItemMaleta(id);
        return ResponseEntity.noContent().build();
    }
}
