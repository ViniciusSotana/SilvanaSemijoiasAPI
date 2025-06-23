package dev.semijoias.silvanasemijoias.relatorios;

import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("relatorios")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class RelatorioController {

    private final RelatorioService relatorioService;
    private final MaletaRepository maletaRepository;

    @GetMapping("/clientes")
    public ResponseEntity<byte[]> gerarRelatorioCliente() {
        return this.relatorioService.gerarRelatorioDeClientes();
    }

    @GetMapping("/vendedoras")
    public ResponseEntity<byte[]> gerarRelatorioVendedora() {
        return this.relatorioService.gerarRelatorioDeVendedoras();
    }

    @GetMapping("/tipo")
    public ResponseEntity<byte[]> gerarRelatorioDeJoiasPorTipo(@RequestParam String descricao) {
        return this.relatorioService.gerarRelatorioDeJoiasPorTipo(descricao);
    }

    @GetMapping("/joias")
    public ResponseEntity<byte[]> buscarJoiasEsgotadas(){
        return this.relatorioService.gerarRelatorioDeJoiasEsgotadas();
    }

    @GetMapping("/gerarRelatorioMaleta/{id}")
    public ResponseEntity<byte[]> gerarRelatorioPorId(@PathVariable Long id) {
        Optional<MaletaModel> maletaOpt = maletaRepository.findById(id);
        if (maletaOpt.isEmpty()) return ResponseEntity.notFound().build();

        return relatorioService.gerarRelatorioDeMaletaPorVendedora(maletaOpt.get());
    }


}
