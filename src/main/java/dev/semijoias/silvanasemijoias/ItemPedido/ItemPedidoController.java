package dev.semijoias.silvanasemijoias.ItemPedido;

import dev.semijoias.silvanasemijoias.Imagem.ImagemDTO;
import dev.semijoias.silvanasemijoias.Imagem.ImagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("itemPedido")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> listarItemPedidos() {
        List<ItemPedidoDTO> itens = itemPedidoService.listar();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> buscarPorId(@PathVariable Long id) {
        ItemPedidoDTO item = itemPedidoService.buscarPorId(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> criarItemPedido(@RequestBody @Valid ItemPedidoDTO itemPedidoDTO) {
        ItemPedidoDTO itemCriado = itemPedidoService.criar(itemPedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> atualizarItemPedido(@PathVariable Long id, @RequestBody @Valid ItemPedidoDTO itemPedidoDTO) {
        ItemPedidoDTO itemAtualizado = itemPedidoService.atualizar(id, itemPedidoDTO);
        if (itemAtualizado != null) {
            return ResponseEntity.ok(itemAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerItemPedido(@PathVariable Long id) {
        if (itemPedidoService.buscarPorId(id) != null) {
            itemPedidoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        }
    }

}
