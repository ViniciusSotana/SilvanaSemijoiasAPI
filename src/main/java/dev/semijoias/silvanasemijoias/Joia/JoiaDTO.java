package dev.semijoias.silvanasemijoias.Joia;


import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoiaDTO {

    private Long id;
    @NotNull(message = "Campo valor unitario é obrigatorio")
    private Double valorUnitario;
    @NotNull(message = "Campo quantia em estoque é obrigatorio")
    private Integer quantidadeEstoque;
    @NotNull(message = "Campo Tipo obrigatorio")
    private Long tipoId;
    @NotNull(message = "Campo quantidade vendida obrigatorio")
    private Integer quantidadeVendida;
    private Long colecaoId;
    @Size(min = 1, message = "Deve conter ao menos uma imagem")
    List<ImagemModel> imagens = new ArrayList<>();


}
