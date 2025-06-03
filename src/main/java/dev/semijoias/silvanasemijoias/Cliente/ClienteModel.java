package dev.semijoias.silvanasemijoias.Cliente;

import dev.semijoias.silvanasemijoias.Pedido.PedidoModel;
import dev.semijoias.silvanasemijoias.Usuario.UsuarioModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel extends UsuarioModel {

    private PedidoModel pedido;
    private Double valorTotalComprado;

}
