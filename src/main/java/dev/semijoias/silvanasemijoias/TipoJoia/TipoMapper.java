package dev.semijoias.silvanasemijoias.TipoJoia;


import org.springframework.stereotype.Component;

@Component
public class TipoMapper {

    public static TipoModel map(TipoRequestDTO tipoDTO) {
        TipoModel tipoModel = new TipoModel();
        tipoModel.setId(tipoDTO.getId());
        tipoModel.setDescricao(tipoDTO.getDescricao());
        tipoModel.setQntVendida(tipoDTO.getQntVendida());
        tipoModel.setJoias(tipoDTO.getJoias());

        return tipoModel;
    }

    public static TipoRequestDTO map(TipoModel tipoModel) {
        TipoRequestDTO tipoDTO = new TipoRequestDTO();
        tipoDTO.setId(tipoModel.getId());
        tipoDTO.setDescricao(tipoModel.getDescricao());
        tipoDTO.setQntVendida(tipoModel.getQntVendida());
        tipoDTO.setJoias(tipoModel.getJoias());

        return tipoDTO;
    }

    public static TipoJoiaResponseDTO mapResponse(TipoModel tipoModel) {
        return new TipoJoiaResponseDTO(
                tipoModel.getId(),
                tipoModel.getDescricao(),
                tipoModel.getQntVendida()
        );
    }



}
