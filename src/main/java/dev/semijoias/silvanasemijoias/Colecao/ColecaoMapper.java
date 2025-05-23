package dev.semijoias.silvanasemijoias.Colecao;

import org.springframework.stereotype.Component;

@Component
public class ColecaoMapper {

    public ColecaoModel map(ColecaoDTO colecaoDTO) {
        ColecaoModel colecaoModel = new ColecaoModel();
        colecaoModel.setId(colecaoDTO.getId());
        colecaoModel.setNome(colecaoDTO.getNome());
        colecaoModel.setJoias(colecaoDTO.getJoias());

        return colecaoModel;
    }

    public ColecaoDTO map(ColecaoModel tipoModel) {
        ColecaoDTO colecaoDTO = new ColecaoDTO();
        colecaoDTO.setId(tipoModel.getId());
        colecaoDTO.setNome(tipoModel.getNome());
        colecaoDTO.setJoias(tipoModel.getJoias());

        return colecaoDTO;
    }

}
