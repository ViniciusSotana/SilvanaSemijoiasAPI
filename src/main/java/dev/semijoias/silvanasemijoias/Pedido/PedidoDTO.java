package dev.semijoias.silvanasemijoias.Pedido;

import dev.semijoias.silvanasemijoias.Cliente.ClienteModel;
import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    @NotNull(message = "cliente obrigatorio")
    private Long clienteId;
    @NotNull(message = "data obrigatorio")
    private LocalDate dataCriacao;
    @NotNull(message = "status obrigatorio")
    private StatusEnum status;
    @NotNull(message = "valor total obrigatorio")
    private Double valorTotal;
    private LocalDate dataFechamento;
    private String observacao;
    private List<ItemPedidoModel> itens;

}
