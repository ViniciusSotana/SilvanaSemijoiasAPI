package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoMapper;
import dev.semijoias.silvanasemijoias.Promocao.PromocaoMapper;
import dev.semijoias.silvanasemijoias.Promocao.PromocaoModel;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoMapper;
import org.springframework.stereotype.Component;

@Component
public class JoiaMapper {
    
    public static JoiaModel map(JoiaRequestDTO joiaDTO, PromocaoModel promocao) {
        JoiaModel joiaModel = new JoiaModel();
        joiaModel.setId(joiaDTO.getId());
        joiaModel.setValorOriginal(joiaDTO.getValorOriginal());
        joiaModel.setValorVenda(joiaDTO.getValorVenda());
        joiaModel.setQuantidadeEstoque(joiaDTO.getQuantidadeEstoque());
        joiaModel.setQuantidadeVendida(joiaDTO.getQuantidadeVendida());
        joiaModel.setImagens(joiaDTO.getImagens());
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
        joiaDTO.setPromocao(PromocaoMapper.mapResponse(joiaModel.getPromocao()));

        return joiaDTO;
    }

    public static JoiaResponseDTO mapResponse(JoiaModel joiaModel) {
        return new JoiaResponseDTO(
                joiaModel.getId(),
                joiaModel.getValorVenda(),
                joiaModel.getQuantidadeEstoque(),
                joiaModel.getImagens(),
                PromocaoMapper.mapResponse(joiaModel.getPromocao()),
                TipoMapper.mapResponse(joiaModel.getTipo()),
                ColecaoMapper.mapResponse(joiaModel.getColecao())
        );
    }

    public static JoiaModel mapResponse(JoiaResponseDTO joiaResponseDTO) {
        return new JoiaModel(
                joiaResponseDTO.getId(),
                null,
                joiaResponseDTO.getValorVenda(),
                joiaResponseDTO.getQuantidadeEstoque(),
                PromocaoMapper.mapModel(joiaResponseDTO.getPromocao()),
                TipoMapper.mapModel(joiaResponseDTO.getTipoJoia()),
                null,
                ColecaoMapper.mapModel(joiaResponseDTO.getColecao()),
                joiaResponseDTO.getImagens()
        );
    }

    public static JoiaModel mapRequest(JoiaRequestDTO joiaRequestDTO) {
        JoiaModel joiaModel = new JoiaModel();
        joiaModel.setId(joiaRequestDTO.getId());
        joiaModel.setValorOriginal(joiaRequestDTO.getValorOriginal());
        joiaModel.setValorVenda(joiaRequestDTO.getValorVenda());
        joiaModel.setQuantidadeEstoque(joiaRequestDTO.getQuantidadeEstoque());
        joiaModel.setQuantidadeVendida(joiaRequestDTO.getQuantidadeVendida());
        joiaModel.setImagens(joiaRequestDTO.getImagens());
        joiaModel.setPromocao(PromocaoMapper.mapModel(joiaRequestDTO.getPromocao()));

        return new JoiaModel();
    }

    public static JoiaResponseDTO mapResponseCerto(JoiaRequestDTO joiaDTO) {
        JoiaResponseDTO joiaResponseDTO = new JoiaResponseDTO();
        joiaResponseDTO.setId(joiaDTO.getId());
        joiaResponseDTO.setValorVenda(joiaDTO.getValorVenda());
        joiaResponseDTO.setQuantidadeEstoque(joiaDTO.getQuantidadeEstoque());
        joiaResponseDTO.setImagens(joiaDTO.getImagens());
        return joiaResponseDTO;
    }

    
}
