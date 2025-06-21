package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoResponseDTO;
import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoJoiaResponseDTO;
import lombok.Data;
import java.util.List;

@Data
public class JoiaResponseDTO {
    private Long id;
    private Double valorVenda;
    private Integer quantidadeEstoque;
    private List<ImagemModel> imagens;

    private TipoJoiaResponseDTO tipoJoia;
    private ColecaoResponseDTO colecao;
}