package dev.semijoias.silvanasemijoias.Administrador;

import dev.semijoias.silvanasemijoias.Usuario.UsuarioModel;
import jakarta.persistence.Entity;

@Entity
public class AdministradorModel extends UsuarioModel {

    private Long idUsuario;
}
