package dev.semijoias.silvanasemijoias.Promocao;

import dev.semijoias.silvanasemijoias.Joia.JoiaMapper;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PromocaoMapper {

    public static PromocaoModel map(PromocaoCreateDTO dto) {
        PromocaoModel model = new PromocaoModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDataInicio(dto.getDataInicio());
        model.setDataFim(dto.getDataFim());
        model.setPercentualDesconto(dto.getPercentualDesconto());
        return model;
    }

    public static PromocaoModel map(PromocaoDTO dto) {
        PromocaoModel model = new PromocaoModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDataInicio(dto.getDataInicio());
        model.setDataFim(dto.getDataFim());
        model.setPercentualDesconto(dto.getPercentualDesconto());
        return model;
    }

    public static PromocaoDTO map(PromocaoModel promocaoModel){
        PromocaoDTO promocaoDTO = new PromocaoDTO();

        promocaoDTO.setId(promocaoModel.getId());
        promocaoDTO.setNome(promocaoModel.getNome());
        promocaoDTO.setDataInicio(promocaoModel.getDataInicio());
        promocaoDTO.setDataFim(promocaoModel.getDataFim());
        promocaoDTO.setPercentualDesconto(promocaoModel.getPercentualDesconto());
        promocaoDTO.setJoiasId(promocaoModel.getJoias().stream().map(JoiaMapper::map).toList());

        return promocaoDTO;
    }

    public static PromocaoJoiaResponse mapResponse(PromocaoModel promocaoModel) {
        return new PromocaoJoiaResponse(
                promocaoModel.getId(),
                promocaoModel.getNome(),
                promocaoModel.getPercentualDesconto(),
                promocaoModel.getDataInicio(),
                promocaoModel.getDataFim()
        );
    }

    public static PromocaoModel mapModel(PromocaoJoiaResponse promocaoJoiaResponse) {
        return new PromocaoModel(
                promocaoJoiaResponse.getId(),
                promocaoJoiaResponse.getNome(),
                promocaoJoiaResponse.getPercentualDesconto(),
                promocaoJoiaResponse.getDataInicio(),
                promocaoJoiaResponse.getDataFim(),
                null
        );
    }


}
