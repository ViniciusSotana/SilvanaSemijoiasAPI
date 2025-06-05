package dev.semijoias.silvanasemijoias.ItemPedido;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Pedido.PedidoModel;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ItemPedidoModel map(ItemPedidoDTO itemPedidoDTO){
        ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
        itemPedidoModel.setId(itemPedidoDTO.getId());

        JoiaModel joia = new JoiaModel();
        joia.setId(itemPedidoDTO.getJoiaId());
        itemPedidoModel.setJoia(joia);

        PedidoModel pedido = new PedidoModel();
        pedido.setId(itemPedidoDTO.getPedidoId());
        itemPedidoModel.setPedido(pedido);

        itemPedidoModel.setQuantidade(itemPedidoDTO.getQuantidade());
        itemPedidoModel.setSubTotal(itemPedidoDTO.getSubTotal());

        return itemPedidoModel;



    }

    public ItemPedidoDTO map(ItemPedidoModel itemPedidoModel){
        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
        itemPedidoDTO.setId(itemPedidoModel.getId());
        itemPedidoDTO.setQuantidade(itemPedidoModel.getQuantidade());
        itemPedidoDTO.setSubTotal(itemPedidoModel.getSubTotal());

        if (itemPedidoModel.getJoia() != null) {
            itemPedidoDTO.setJoiaId(itemPedidoModel.getJoia().getId());
        }

        if (itemPedidoModel.getPedido() != null) {
            itemPedidoDTO.setPedidoId(itemPedidoModel.getPedido().getId());
        }

        return itemPedidoDTO;
    }

}
