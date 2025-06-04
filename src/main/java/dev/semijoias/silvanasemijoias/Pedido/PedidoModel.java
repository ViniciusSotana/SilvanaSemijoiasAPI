package dev.semijoias.silvanasemijoias.Pedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.semijoias.silvanasemijoias.Cliente.ClienteModel;
import dev.semijoias.silvanasemijoias.ItemPedido.ItemPedidoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private ClienteModel cliente;
    private LocalDate dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private Double valorTotal;
    private LocalDate dataFechamento;
    private String observacao;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemPedidoModel> itens;


}
