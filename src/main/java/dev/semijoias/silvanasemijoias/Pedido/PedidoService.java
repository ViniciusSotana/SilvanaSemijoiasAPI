package dev.semijoias.silvanasemijoias.Pedido;

import dev.semijoias.silvanasemijoias.Cliente.ClienteModel;
import dev.semijoias.silvanasemijoias.Cliente.ClienteRepository;
import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoModel;
import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoDTO criarPedido(PedidoDTO dto) {
        ClienteModel cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        PedidoModel model = pedidoMapper.map(dto, cliente); // << Passa o cliente direto pro mapper

        PedidoModel salvo = pedidoRepository.save(model);
        return pedidoMapper.map(salvo);
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

    @Transactional
    public void deletarPedido(Long id) {
        PedidoModel pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.getItens().clear();
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
