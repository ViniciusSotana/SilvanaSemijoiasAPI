package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaRepository;
import dev.semijoias.silvanasemijoias.Maleta.MaletaService;
import dev.semijoias.silvanasemijoias.relatorios.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("itensMaleta")
@CrossOrigin
public class ItemMaletaController {

    private final ItemMaletaService itemMaletaService;
    private final RelatorioService relatorioService;
    private final MaletaRepository maletaRepository;

    public ItemMaletaController(ItemMaletaService itemMaletaService, RelatorioService relatorioService, MaletaService maletaService, MaletaRepository maletaRepository) {
        this.itemMaletaService = itemMaletaService;
        this.relatorioService = relatorioService;
        this.maletaRepository = maletaRepository;
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


    @GetMapping("/gerarRelatorioMaleta/{id}")
    public ResponseEntity<byte[]> gerarRelatorioPorId(@PathVariable Long id) {
        Optional<MaletaModel> maletaOpt = maletaRepository.findById(id);
        if (maletaOpt.isEmpty()) return ResponseEntity.notFound().build();

        return relatorioService.gerarRelatorioDeMaletaPorVendedora(maletaOpt.get());
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
