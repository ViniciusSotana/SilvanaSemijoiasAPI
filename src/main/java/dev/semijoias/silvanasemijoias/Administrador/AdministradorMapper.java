package dev.semijoias.silvanasemijoias.Administrador;

import dev.semijoias.silvanasemijoias.Usuario.TipoUsuario;
import org.springframework.stereotype.Component;

@Component
public class AdministradorMapper {

    public AdministradorModel map(AdministradorDTO administradorDTO) {
        AdministradorModel administradorModel = new AdministradorModel();
        administradorModel.setId(administradorDTO.getId());
        administradorModel.setNome(administradorDTO.getNome());
        administradorModel.setEmail(administradorDTO.getEmail());
        administradorModel.setTelefone(administradorDTO.getTelefone());
        administradorModel.setCpf(administradorDTO.getCpf());
        administradorModel.setCargo(administradorDTO.getCargo());
        administradorModel.setEndereco(administradorDTO.getEndereco());
        administradorModel.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        administradorModel.setAtivo(administradorDTO.getAtivo());

        return administradorModel;
    }

    public AdministradorDTO map(AdministradorModel administradorModel) {
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setId(administradorModel.getId());
        administradorDTO.setNome(administradorModel.getNome());
        administradorDTO.setEmail(administradorModel.getEmail());
        administradorDTO.setTelefone(administradorModel.getTelefone());
        administradorDTO.setCpf(administradorModel.getCpf());
        administradorDTO.setCargo(administradorModel.getCargo());
        administradorDTO.setEndereco(administradorModel.getEndereco());
        administradorDTO.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        administradorDTO.setAtivo(administradorModel.getAtivo());

        return administradorDTO;
    }

}
