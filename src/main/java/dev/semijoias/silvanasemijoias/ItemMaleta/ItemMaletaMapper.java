package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Joia.JoiaMapper;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import dev.semijoias.silvanasemijoias.Maleta.MaletaMapper;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import org.springframework.stereotype.Component;

@Component
public class ItemMaletaMapper {

    private final JoiaRepository joiaRepo;

    public ItemMaletaMapper(JoiaRepository joiaRepo) {
        this.joiaRepo = joiaRepo;
    }

    private JoiaModel buscarJoia(Long id) {
        return this.joiaRepo.findById(id).orElseThrow(() -> new RuntimeException("Joia nao encontrada"));
    }


    public static ItemMaletaModel map(ItemMaletaDTO itemMaletaDTO, JoiaModel joiaModel, MaletaModel maletaModel) {
        ItemMaletaModel itemMaletaModel = new ItemMaletaModel();

        itemMaletaModel.setId(itemMaletaDTO.getId());
        itemMaletaModel.setQuantidade(itemMaletaDTO.getQuantidade());
        itemMaletaModel.setDataInsercao(itemMaletaDTO.getDataInsercao());

        itemMaletaModel.setJoia(joiaModel);
        itemMaletaModel.setMaleta(maletaModel);

        return itemMaletaModel;
    }

    public static ItemMaletaDTO map(ItemMaletaModel itemMaletaModel) {
        ItemMaletaDTO itemMaletaDTO = new ItemMaletaDTO();
        itemMaletaDTO.setId(itemMaletaModel.getId());
        itemMaletaDTO.setJoiaId(itemMaletaModel.getJoia().getId());
        itemMaletaDTO.setMaletaId(itemMaletaModel.getMaleta().getId());
        itemMaletaDTO.setQuantidade(itemMaletaModel.getQuantidade());
        itemMaletaDTO.setDataInsercao(itemMaletaModel.getDataInsercao());

        return itemMaletaDTO;
    }

    public static ItemMaletaAtualizadoDTO mapResponse(ItemMaletaModel itemMaletaModel) {
        ItemMaletaAtualizadoDTO itemMaletaDTO = new ItemMaletaAtualizadoDTO();
        itemMaletaDTO.setId(itemMaletaModel.getId());
        itemMaletaDTO.setJoia(JoiaMapper.mapResponse(itemMaletaModel.getJoia()));
        itemMaletaDTO.setQuantidade(itemMaletaModel.getQuantidade());
        itemMaletaDTO.setDataInsercao(itemMaletaModel.getDataInsercao());

        return itemMaletaDTO;
    }

    public static ItemMaletaModel mapModel(ItemMaletaAtualizadoDTO itemMaletaAtualizadoDTO) {
        ItemMaletaModel itemMaletaModel = new ItemMaletaModel();
        itemMaletaModel.setId(itemMaletaAtualizadoDTO.getId());
        itemMaletaModel.setJoia(JoiaMapper.mapResponse(itemMaletaAtualizadoDTO.getJoia()));
        itemMaletaModel.setQuantidade(itemMaletaAtualizadoDTO.getQuantidade());
        itemMaletaModel.setDataInsercao(itemMaletaAtualizadoDTO.getDataInsercao());

        return itemMaletaModel;
    }


}
