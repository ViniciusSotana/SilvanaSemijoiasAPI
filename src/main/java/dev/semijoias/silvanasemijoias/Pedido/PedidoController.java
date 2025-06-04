package dev.semijoias.silvanasemijoias.Pedido;

import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criar(@RequestBody PedidoDTO pedido) {
        PedidoDTO criado = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<PedidoModel>> listar() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoModel> atualizar(@PathVariable Long id, @RequestBody PedidoModel pedido) {
        return ResponseEntity.ok(pedidoService.atualizarPedido(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<PedidoModel> adicionarItem(@PathVariable Long id, @RequestBody ItemPedidoModel item) {
        return ResponseEntity.ok(pedidoService.adicionarItemAoPedido(id, item));
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    public ResponseEntity<PedidoModel> removerItem(@PathVariable Long id, @PathVariable Long itemId) {
        return ResponseEntity.ok(pedidoService.removerItemDoPedido(id, itemId));
    }
}
