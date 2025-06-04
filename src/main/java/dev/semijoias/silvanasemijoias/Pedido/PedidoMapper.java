package dev.semijoias.silvanasemijoias.Pedido;

import dev.semijoias.silvanasemijoias.Cliente.ClienteModel;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoModel map(PedidoDTO dto, ClienteModel cliente) {
        PedidoModel model = new PedidoModel();
        model.setId(dto.getId());
        model.setCliente(cliente);
        model.setDataCriacao(dto.getDataCriacao());
        model.setStatus(dto.getStatus());
        model.setValorTotal(dto.getValorTotal());
        model.setDataFechamento(dto.getDataFechamento());
        model.setObservacao(dto.getObservacao());
        model.setItens(dto.getItens());
        return model;
    }

    public PedidoDTO map(PedidoModel pedidoModel){
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedidoModel.getId());
        pedidoDTO.setClienteId(pedidoModel.getCliente().getId());
        pedidoDTO.setStatus(pedidoModel.getStatus());
        pedidoDTO.setDataCriacao(pedidoModel.getDataCriacao());
        pedidoDTO.setDataFechamento(pedidoModel.getDataFechamento());
        pedidoDTO.setValorTotal(pedidoModel.getValorTotal());
        pedidoDTO.setObservacao(pedidoModel.getObservacao());
        pedidoDTO.setItens(pedidoModel.getItens());

        return pedidoDTO;
    }

}
