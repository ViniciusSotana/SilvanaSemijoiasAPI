package dev.semijoias.silvanasemijoias.TipoJoia;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDTO {

    private Long id;
    @NotBlank(message = "Descricao obrigatoria")
    private String descricao;
    @NotBlank(message = "Quantidade vendida obrigatoria")
    private Integer qntVendida;

}
