package dev.semijoias.silvanasemijoias.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoModel map(PedidoDTO pedidoDTO){
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setId(pedidoDTO.getId());
        pedidoModel.setCliente(pedidoDTO.getCliente());
        pedidoModel.setStatus(pedidoDTO.getStatus());
        pedidoModel.setDataCriacao(pedidoDTO.getDataCriacao());
        pedidoModel.setDataFechamento(pedidoDTO.getDataFechamento());
        pedidoModel.setValorTotal(pedidoDTO.getValorTotal());
        pedidoModel.setObservacao(pedidoDTO.getObservacao());
        pedidoModel.setItens(pedidoDTO.getItens());

        return pedidoModel;
    }

    public PedidoDTO map(PedidoModel pedidoModel){
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedidoModel.getId());
        pedidoDTO.setCliente(pedidoModel.getCliente());
        pedidoDTO.setStatus(pedidoModel.getStatus());
        pedidoDTO.setDataCriacao(pedidoModel.getDataCriacao());
        pedidoDTO.setDataFechamento(pedidoModel.getDataFechamento());
        pedidoDTO.setValorTotal(pedidoModel.getValorTotal());
        pedidoDTO.setObservacao(pedidoModel.getObservacao());
        pedidoDTO.setItens(pedidoModel.getItens());

        return pedidoDTO;
    }

}
