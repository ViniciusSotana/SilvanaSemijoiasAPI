package dev.semijoias.silvanasemijoias.Colecao;

import org.springframework.stereotype.Component;

@Component
public class ColecaoMapper {

    public static ColecaoModel map(ColecaoRequestDTO colecaoDTO) {
        ColecaoModel colecaoModel = new ColecaoModel();
        colecaoModel.setId(colecaoDTO.getId());
        colecaoModel.setNome(colecaoDTO.getNome());
        colecaoModel.setJoias(colecaoDTO.getJoias());

        return colecaoModel;
    }

    public static ColecaoRequestDTO map(ColecaoModel tipoModel) {
        ColecaoRequestDTO colecaoDTO = new ColecaoRequestDTO();
        colecaoDTO.setId(tipoModel.getId());
        colecaoDTO.setNome(tipoModel.getNome());
        colecaoDTO.setJoias(tipoModel.getJoias());

        return colecaoDTO;
    }

    public static ColecaoResponseDTO mapResponse(ColecaoModel tipoModel) {
        return new ColecaoResponseDTO(
                tipoModel.getId(),
                tipoModel.getNome()
        );
    }

}
