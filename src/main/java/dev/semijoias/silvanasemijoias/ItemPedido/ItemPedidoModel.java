package dev.semijoias.silvanasemijoias.ItemPedido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long joiaId;
    private Long pedidoId;
    private Integer quantidade;
    private Double valorUnitario;
    private Double subTotal;

}
