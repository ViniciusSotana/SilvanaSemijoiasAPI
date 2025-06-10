package dev.semijoias.silvanasemijoias.Cliente;

import dev.semijoias.silvanasemijoias.relatorios.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

    private final ClienteService clienteService;
    private final RelatorioService relatorioService;

    @PostMapping
    public ClienteDTO criar(@RequestBody ClienteDTO dto) {
        return clienteService.criar(dto);
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @GetMapping("/relatorios")
    public ResponseEntity<byte[]> gerarRelatorioCliente() {
        return this.relatorioService.gerarRelatorioDeClientes();
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return clienteService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}
