package dev.semijoias.silvanasemijoias.ItemPedido;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import dev.semijoias.silvanasemijoias.Pedido.PedidoModel;
import dev.semijoias.silvanasemijoias.Pedido.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoMapper itemPedidoMapper;
    private final JoiaRepository joiaRepository;
    private final PedidoRepository pedidoRepository;


    public ItemPedidoDTO criar(ItemPedidoDTO dto) {
        ItemPedidoModel model = itemPedidoMapper.map(dto);

        JoiaModel joia = joiaRepository.findById(model.getJoia().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joia não encontrada"));
        model.setJoia(joia);

        PedidoModel pedido = pedidoRepository.findById(model.getPedido().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        model.setPedido(pedido);

        model.setSubTotal(model.getQuantidade() * model.getJoia().getValorVenda());

        ItemPedidoModel salvo = itemPedidoRepository.save(model);
        return itemPedidoMapper.map(salvo);
    }

    public List<ItemPedidoDTO> listar() {
        return itemPedidoRepository.findAll().stream()
                .map(itemPedidoMapper::map)
                .toList();
    }

    public ItemPedidoDTO buscarPorId(Long id) {
        ItemPedidoModel cliente = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return itemPedidoMapper.map(cliente);
    }

    public ItemPedidoDTO atualizar(Long id, ItemPedidoDTO dto) {
        ItemPedidoModel item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        item.setQuantidade(dto.getQuantidade());
        item.setSubTotal(dto.getSubTotal());
        item.setId(dto.getId());

        JoiaModel joia = joiaRepository.findById(item.getJoia().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joia não encontrada"));
        item.setJoia(joia);

        PedidoModel pedido = pedidoRepository.findById(item.getPedido().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        item.setPedido(pedido);


        ItemPedidoModel atualizado = itemPedidoRepository.save(item);
        return itemPedidoMapper.map(atualizado);
    }

    public void deletar(Long id) {
        itemPedidoRepository.deleteById(id);
    }
    
}
