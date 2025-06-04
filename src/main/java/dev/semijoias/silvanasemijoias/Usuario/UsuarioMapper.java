package dev.semijoias.silvanasemijoias.Usuario;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioModel map(UsuarioDTO usuarioDTO) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuarioDTO.getId());
        usuarioModel.setNome(usuarioDTO.getNome());
        usuarioModel.setCpf(usuarioDTO.getCpf());
        usuarioModel.setEmail(usuarioDTO.getEmail());
        usuarioModel.setSenha(usuarioDTO.getSenha());
        usuarioModel.setEndereco(usuarioDTO.getEndereco());
        usuarioModel.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuarioModel.setTelefone(usuarioDTO.getTelefone());
        return usuarioModel;
    }

    public UsuarioDTO map(UsuarioModel usuarioModel) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuarioModel.getId());
        usuarioDTO.setNome(usuarioModel.getNome());
        usuarioDTO.setCpf(usuarioModel.getCpf());
        usuarioDTO.setEmail(usuarioModel.getEmail());
        usuarioDTO.setSenha(usuarioModel.getSenha());
        usuarioDTO.setEndereco(usuarioModel.getEndereco());
        usuarioDTO.setTipoUsuario(usuarioModel.getTipoUsuario());
        usuarioDTO.setTelefone(usuarioModel.getTelefone());
        return usuarioDTO;
    }
}
