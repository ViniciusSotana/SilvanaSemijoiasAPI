package dev.semijoias.silvanasemijoias.Imagem;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagemDTO {

    private Long id;
    @NotBlank(message = "URL obrigatoria")
    private String urlImagem;
    @NotNull(message = "Campo joia obrigatorio")
    private Long joiaId;

}
