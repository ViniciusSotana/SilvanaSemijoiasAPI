package dev.semijoias.silvanasemijoias.TipoJoia;


import org.springframework.stereotype.Component;

@Component
public class TipoMapper {

    public TipoModel map(TipoRequestDTO tipoDTO) {
        TipoModel tipoModel = new TipoModel();
        tipoModel.setId(tipoDTO.getId());
        tipoModel.setDescricao(tipoDTO.getDescricao());
        tipoModel.setQntVendida(tipoDTO.getQntVendida());
        tipoModel.setJoias(tipoDTO.getJoias());

        return tipoModel;
    }

    public TipoRequestDTO map(TipoModel tipoModel) {
        TipoRequestDTO tipoDTO = new TipoRequestDTO();
        tipoDTO.setId(tipoModel.getId());
        tipoDTO.setDescricao(tipoModel.getDescricao());
        tipoDTO.setQntVendida(tipoModel.getQntVendida());
        tipoDTO.setJoias(tipoModel.getJoias());

        return tipoDTO;
    }

}
