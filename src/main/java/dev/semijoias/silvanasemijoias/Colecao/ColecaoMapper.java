package dev.semijoias.silvanasemijoias.Colecao;

import dev.semijoias.silvanasemijoias.Joia.JoiaMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class ColecaoMapper {

    public static ColecaoModel map(ColecaoRequestDTO colecaoDTO) {
        ColecaoModel colecaoModel = new ColecaoModel();
        colecaoModel.setId(colecaoDTO.getId());
        colecaoModel.setNome(colecaoDTO.getNome());
        colecaoModel.setJoias(colecaoDTO.getJoias());
        colecaoModel.setDataLancamento(LocalDate.now());

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
                tipoModel.getNome(),
                tipoModel.getJoias().stream().map(JoiaMapper::map).toList(),
                tipoModel.getDataLancamento()
        );
    }

    public static ColecaoModel mapModel(ColecaoResponseDTO colecaoResponseDTO) {
        return new ColecaoModel(
                colecaoResponseDTO.getId(),
                colecaoResponseDTO.getNome(),
                colecaoResponseDTO.getJoias().stream().map(JoiaMapper::mapRequest).toList(),
                colecaoResponseDTO.getDataLancamento()
        );
    }


}
