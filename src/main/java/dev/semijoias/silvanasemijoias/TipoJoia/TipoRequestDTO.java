package dev.semijoias.silvanasemijoias.TipoJoia;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoRequestDTO {

    private Long id;
    @NotBlank(message = "Descricao obrigatoria")
    private String descricao;
    @NotNull(message = "Quantidade vendida obrigatoria")
    private Integer qntVendida;
    @NotNull(message = "Campo joia obrigatorio")
    private List<JoiaModel> joias = new ArrayList<>();

}
