package dev.semijoias.silvanasemijoias.Pedido;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private Date dataCriacao;
    private StatusEnum status;
    private Double valorTotal;
    private Date dataFechamento;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoModel> itens;


}
