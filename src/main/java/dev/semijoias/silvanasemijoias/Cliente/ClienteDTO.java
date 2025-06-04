package dev.semijoias.silvanasemijoias.Cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.semijoias.silvanasemijoias.Pedido.PedidoModel;
import dev.semijoias.silvanasemijoias.Usuario.UsuarioDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends UsuarioDTO {

    @JsonIgnore

    private List<PedidoModel> pedidos;

    private Double valorTotalComprado = 0.0;
    private Integer numeroDePedidos;
    @NotNull
    private LocalDate dataCadastro;


}
