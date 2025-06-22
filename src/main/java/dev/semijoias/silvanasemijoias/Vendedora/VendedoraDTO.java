package dev.semijoias.silvanasemijoias.Vendedora;

import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Usuario.UsuarioDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedoraDTO extends UsuarioDTO {

    private Long maletaId;
    @Min(value = 0, message = "Comissão deve ser no mínimo 0")
    @NotNull(message = "comissao obrigatoria")
    private Double comissao;

}
