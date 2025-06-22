package dev.semijoias.silvanasemijoias.Maleta;

import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaMapper;
import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraMapper;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MaletaMapper {


    public static MaletaModel map(MaletaDTO maletaDTO) {

        MaletaModel maletaModel = new MaletaModel();
        maletaModel.setId(maletaDTO.getId());
        maletaModel.setVendedora(maletaDTO.getVendedora());
        maletaModel.setStatus(maletaDTO.getStatus());
        maletaModel.setDataDevolucao(maletaDTO.getDataDevolucao());
        maletaModel.setDataEntrega(maletaDTO.getDataEntrega());

        return maletaModel;
    }

    public static MaletaModel map(MaletaVendedoraDTO maletaDTO, VendedoraModel vendedoraModel, List<ItemMaletaModel> listItemMaletaModel) {
        return new MaletaModel(
                null,
                vendedoraModel,
                maletaDTO.getStatus(),
                maletaDTO.getDataEntrega(),
                maletaDTO.getDataDevolucao(),
                listItemMaletaModel
        );
    }

    public static MaletaDTO map(MaletaModel maletaModel) {
        MaletaDTO maletaDTO = new MaletaDTO();
        maletaDTO.setId(maletaModel.getId());
        maletaDTO.setVendedora(maletaModel.getVendedora());
        maletaDTO.setStatus(maletaModel.getStatus());
        maletaDTO.setDataDevolucao(maletaModel.getDataDevolucao());
        maletaDTO.setDataEntrega(maletaModel.getDataEntrega());
        maletaDTO.setItens(maletaModel.getItens().stream().map(ItemMaletaMapper::mapResponse).toList());
        return maletaDTO;
    }

    public static MaletaModel mapRequest(MaletaRequestDTO maletaRequestDTO) {
        MaletaModel maletaModel = new MaletaModel();
        maletaModel.setDataDevolucao(maletaRequestDTO.getDataDevolucao());
        maletaModel.setDataEntrega(maletaRequestDTO.getDataEntrega());


        return maletaModel;
    }

    public static MaletaResponseDTO mapResponse(MaletaModel maletaModel) {
        MaletaResponseDTO maletaResponseDTO = new MaletaResponseDTO();
        maletaResponseDTO.setId(maletaModel.getId());
        maletaResponseDTO.setVendedora(VendedoraMapper.map(maletaModel.getVendedora()));
        maletaResponseDTO.setDataDevolucao(maletaModel.getDataDevolucao());
        maletaResponseDTO.setDataEntrega(maletaModel.getDataEntrega());
        maletaResponseDTO.setItens(maletaModel.getItens().stream().map(ItemMaletaMapper::mapResponse).toList());
        return maletaResponseDTO;
    }


}
