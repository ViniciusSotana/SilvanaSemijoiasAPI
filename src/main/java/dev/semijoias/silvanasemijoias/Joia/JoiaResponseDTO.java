package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoResponseDTO;
import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import dev.semijoias.silvanasemijoias.Promocao.PromocaoJoiaResponse;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoJoiaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoiaResponseDTO {
    private Long id;
    private Double valorVenda;
    private Integer quantidadeEstoque;
    private List<ImagemModel> imagens;
    private PromocaoJoiaResponse promocao;
    private TipoJoiaResponseDTO tipoJoia;
    private ColecaoResponseDTO colecao;
}