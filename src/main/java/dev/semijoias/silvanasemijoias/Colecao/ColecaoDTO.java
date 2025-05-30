package dev.semijoias.silvanasemijoias.Colecao;

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
public class ColecaoDTO {

    private Long id;
    @NotBlank(message = "Nome obrigatorio")
    private String nome;
    @NotNull(message = "Campo joias obrigatorio")
    private List<JoiaModel> joias = new ArrayList<>();

}
