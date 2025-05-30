package dev.semijoias.silvanasemijoias.Vendedora;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Usuario.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "usuario_id")
public class VendedoraModel extends UsuarioModel {
    @JoinColumn(name = "maleta")
    private Long idMaleta;
    private Double comissao;

}
