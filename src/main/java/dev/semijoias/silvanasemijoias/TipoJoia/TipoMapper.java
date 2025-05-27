package dev.semijoias.silvanasemijoias.TipoJoia;


import org.springframework.stereotype.Component;

@Component
public class TipoMapper {

    public TipoModel map(TipoDTO tipoDTO) {
        TipoModel tipoModel = new TipoModel();
        tipoModel.setId(tipoDTO.getId());
        tipoModel.setDescricao(tipoDTO.getDescricao());
        tipoModel.setQntVendida(tipoDTO.getQntVendida());
        tipoModel.setJoias(tipoDTO.getJoias());

        return tipoModel;
    }

    public TipoDTO map(TipoModel tipoModel) {
        TipoDTO tipoDTO = new TipoDTO();
        tipoDTO.setId(tipoModel.getId());
        tipoDTO.setDescricao(tipoModel.getDescricao());
        tipoDTO.setQntVendida(tipoModel.getQntVendida());
        tipoDTO.setJoias(tipoModel.getJoias());

        return tipoDTO;
    }

}
