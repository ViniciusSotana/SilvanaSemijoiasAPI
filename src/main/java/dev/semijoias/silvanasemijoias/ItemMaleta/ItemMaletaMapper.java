package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import org.springframework.stereotype.Component;

@Component
public class ItemMaletaMapper {

    public ItemMaletaModel map(ItemMaletaDTO itemMaletaDTO) {
        ItemMaletaModel itemMaletaModel = new ItemMaletaModel();
        itemMaletaModel.setId(itemMaletaDTO.getId());
        itemMaletaModel.setQuantidade(itemMaletaDTO.getQuantidade());
        itemMaletaModel.setPrecoSugerido(itemMaletaDTO.getPrecoSugerido());
        itemMaletaModel.setDataInsercao(itemMaletaDTO.getDataInsercao());

        MaletaModel maleta = new MaletaModel();
        maleta.setId(itemMaletaDTO.getMaletaId());
        itemMaletaModel.setMaleta(maleta);

        JoiaModel joia = new JoiaModel();
        joia.setId(itemMaletaDTO.getJoiaId());
        itemMaletaModel.setJoia(joia);

        return itemMaletaModel;
    }

    public ItemMaletaDTO map(ItemMaletaModel itemMaletaModel) {
        ItemMaletaDTO itemMaletaDTO = new ItemMaletaDTO();
        itemMaletaDTO.setId(itemMaletaModel.getId());
        itemMaletaDTO.setMaletaId(itemMaletaModel.getMaleta().getId());
        itemMaletaDTO.setJoiaId(itemMaletaModel.getJoia().getId());
        itemMaletaDTO.setQuantidade(itemMaletaModel.getQuantidade());
        itemMaletaDTO.setPrecoSugerido(itemMaletaModel.getPrecoSugerido());
        itemMaletaDTO.setDataInsercao(itemMaletaModel.getDataInsercao());

        return itemMaletaDTO;
    }

}
