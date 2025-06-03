package dev.semijoias.silvanasemijoias.Promocao;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromocaoMapper {

    private final JoiaRepository joiaRepository;

    public PromocaoMapper(JoiaRepository joiaRepository) {
        this.joiaRepository = joiaRepository;
    }


    public PromocaoModel map(PromocaoDTO dto) {
        PromocaoModel model = new PromocaoModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDataInicio(dto.getDataInicio());
        model.setDataFim(dto.getDataFim());
        model.setPercentualDesconto(dto.getPercentualDesconto());

        List<JoiaModel> joias = dto.getJoiasId().stream()
                .map(id -> joiaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Joia não encontrada com ID: " + id)))
                .toList();

        model.setJoias(joias);
        return model;
    }

    public PromocaoDTO map(PromocaoModel promocaoModel){
        PromocaoDTO promocaoDTO = new PromocaoDTO();

        promocaoDTO.setId(promocaoModel.getId());
        promocaoDTO.setNome(promocaoModel.getNome());
        promocaoDTO.setDataInicio(promocaoModel.getDataInicio());
        promocaoDTO.setDataFim(promocaoModel.getDataFim());
        promocaoDTO.setPercentualDesconto(promocaoModel.getPercentualDesconto());
        promocaoDTO.setJoiasId(promocaoModel.getJoias()
                .stream()
                .map(JoiaModel::getId)
                .toList()
        );

        return promocaoDTO;
    }

}
