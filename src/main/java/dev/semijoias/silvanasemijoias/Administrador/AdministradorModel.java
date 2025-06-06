package dev.semijoias.silvanasemijoias.Administrador;

import dev.semijoias.silvanasemijoias.Usuario.UsuarioModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorModel extends UsuarioModel {

    private String cargo;
    private Boolean ativo = true;
}
