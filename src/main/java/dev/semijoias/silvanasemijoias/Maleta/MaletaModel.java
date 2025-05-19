package dev.semijoias.silvanasemijoias.Maleta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class MaletaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vendedoraId;
    private String status;
    private Date dataEntrega;
    private Date dataDevolucao;

}
