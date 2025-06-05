package dev.semijoias.silvanasemijoias.ItemPedido;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Pedido.PedidoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {

    private Long id;
    private Long joiaId;
    private Long pedidoId;
    private Integer quantidade;
    private Double subTotal;

}
