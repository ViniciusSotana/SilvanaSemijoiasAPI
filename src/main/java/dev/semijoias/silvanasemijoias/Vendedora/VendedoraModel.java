package dev.semijoias.silvanasemijoias.Vendedora;

import dev.semijoias.silvanasemijoias.Usuario.UsuarioModel;
import jakarta.persistence.Entity;

@Entity
public class VendedoraModel extends UsuarioModel {

    private Integer idMaleta;
    private Integer idUsuario;
    private Double comissao;

}
