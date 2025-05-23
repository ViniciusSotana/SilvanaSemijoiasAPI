package dev.semijoias.silvanasemijoias.Joia;


import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Campo valor unitario é obrigatorio")
    private Double valorUnitario;
    @NotBlank(message = "Campo quantia em estoque é obrigatorio")
    private Integer quantidadeEstoque;
    @NotBlank(message = "Campo Tipo obrigatorio")
    private Long idTipo;
    @NotBlank(message = "Campo quantidade vendida obrigatorio")
    private Integer quantidadeVendida;
    private Long idColecao;
    @NotNull(message = "Campo imagem obrigatorio")
    List<ImagemModel> imagens = new ArrayList<>();


}
