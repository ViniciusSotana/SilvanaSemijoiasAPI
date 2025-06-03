package dev.semijoias.silvanasemijoias.Pedido;

import dev.semijoias.silvanasemijoias.Cliente.ClienteRepository;
import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoModel;
import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoModel criarPedido(PedidoModel pedido) {
        pedido.setDataCriacao(LocalDate.now());
        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

    public PedidoModel atualizarPedido(Long id, PedidoModel atualizacao) {
        PedidoModel existente = buscarPorId(id);
        existente.setStatus(atualizacao.getStatus());
        existente.setDataFechamento(atualizacao.getDataFechamento());
        existente.setValorTotal(atualizacao.getValorTotal());
        existente.setItens(atualizacao.getItens());
        return pedidoRepository.save(existente);
    }

    public void deletarPedido(Long id) {
        PedidoModel pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }

    public PedidoModel adicionarItemAoPedido(Long pedidoId, ItemPedidoModel item) {
        PedidoModel pedido = buscarPorId(pedidoId);
        item.setPedido(pedido);
        pedido.getItens().add(item);
        itemPedidoRepository.save(item);
        return pedidoRepository.save(pedido);
    }

    public PedidoModel removerItemDoPedido(Long pedidoId, Long itemId) {
        PedidoModel pedido = buscarPorId(pedidoId);
        ItemPedidoModel item = itemPedidoRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        if (pedido.getItens().removeIf(i -> i.getId().equals(itemId))) {
            itemPedidoRepository.delete(item);
        }

        return pedidoRepository.save(pedido);
    }
}
