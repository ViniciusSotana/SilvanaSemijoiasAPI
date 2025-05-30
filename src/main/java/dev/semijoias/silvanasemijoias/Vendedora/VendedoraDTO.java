package dev.semijoias.silvanasemijoias.Vendedora;

import dev.semijoias.silvanasemijoias.Usuario.UsuarioDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedoraDTO extends UsuarioDTO {

    @jakarta.validation.constraints.Positive(message = "ID da maleta deve ser um número positivo")
    @NotNull(message = "maleta obrigatorio")
    private Integer idMaleta;
    @jakarta.validation.constraints.Min(value = 0, message = "Comissão deve ser no mínimo 0")
    @NotNull(message = "comissao obrigatoria")
    private Double comissao;

}
