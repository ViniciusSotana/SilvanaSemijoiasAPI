package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoMapper;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoMapper;
import org.springframework.stereotype.Component;

@Component
public class JoiaMapper {
    
    public static JoiaModel map(JoiaRequestDTO joiaDTO){
        JoiaModel joiaModel = new JoiaModel();
        joiaModel.setId(joiaDTO.getId());
        joiaModel.setValorOriginal(joiaDTO.getValorOriginal());
        joiaModel.setValorVenda(joiaDTO.getValorVenda());
        joiaModel.setQuantidadeEstoque(joiaDTO.getQuantidadeEstoque());
        joiaModel.setQuantidadeVendida(joiaDTO.getQuantidadeVendida());
        joiaModel.setImagens(joiaDTO.getImagens());
        joiaModel.setPromocao(joiaDTO.getPromocao());
        return joiaModel;
    }

    public static JoiaRequestDTO map(JoiaModel joiaModel) {
        JoiaRequestDTO joiaDTO = new JoiaRequestDTO();
        joiaDTO.setId(joiaModel.getId());
        joiaDTO.setValorOriginal(joiaModel.getValorOriginal());
        joiaDTO.setValorVenda(joiaModel.getValorVenda());
        joiaDTO.setQuantidadeEstoque(joiaModel.getQuantidadeEstoque());
        joiaDTO.setQuantidadeVendida(joiaModel.getQuantidadeVendida());
        joiaDTO.setImagens(joiaModel.getImagens());
        if (joiaModel.getTipo() != null) {
            joiaDTO.setTipoId(joiaModel.getTipo().getId());
        }

        if (joiaModel.getColecao() != null) {
            joiaDTO.setColecaoId(joiaModel.getColecao().getId());
        }
        joiaDTO.setPromocao(joiaModel.getPromocao());

        return joiaDTO;
    }

    public static JoiaResponseDTO mapResponse(JoiaModel joiaModel) {
        return new JoiaResponseDTO(
                joiaModel.getId(),
                joiaModel.getValorVenda(),
                joiaModel.getQuantidadeEstoque(),
                joiaModel.getImagens(),
                TipoMapper.mapResponse(joiaModel.getTipo()),
                ColecaoMapper.mapResponse(joiaModel.getColecao())
        );
    }


    
}
