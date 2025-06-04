package dev.semijoias.silvanasemijoias.Cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.semijoias.silvanasemijoias.Pedido.PedidoModel;
import dev.semijoias.silvanasemijoias.Usuario.UsuarioModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel extends UsuarioModel {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PedidoModel> pedidos;

    private Double valorTotalComprado = 0.0;
    private Integer numeroDePedidos;
    private LocalDate dataCadastro;

}
