package dev.semijoias.silvanasemijoias.Administrador;


import dev.semijoias.silvanasemijoias.Usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO extends UsuarioDTO {

    private String cargo;
    private Boolean ativo = true;

}
