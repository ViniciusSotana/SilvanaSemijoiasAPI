package dev.semijoias.silvanasemijoias.Maleta;

import org.springframework.stereotype.Component;

@Component
public class MaletaMapper {

    public MaletaModel map(MaletaDTO maletaDTO) {
        MaletaModel maletaModel = new MaletaModel();
        maletaModel.setId(maletaDTO.getId());
        maletaModel.setVendedora(maletaDTO.getVendedora());
        maletaModel.setStatus(maletaDTO.getStatus());
        maletaModel.setDataDevolucao(maletaDTO.getDataDevolucao());
        maletaModel.setDataEntrega(maletaDTO.getDataEntrega());

        return maletaModel;
    }

    public MaletaDTO map(MaletaModel maletaModel) {
        MaletaDTO maletaDTO = new MaletaDTO();
        maletaDTO.setId(maletaModel.getId());
        maletaDTO.setVendedora(maletaModel.getVendedora());
        maletaDTO.setStatus(maletaModel.getStatus());
        maletaDTO.setDataDevolucao(maletaModel.getDataDevolucao());
        maletaDTO.setDataEntrega(maletaModel.getDataEntrega());
        return maletaDTO;
    }


}
