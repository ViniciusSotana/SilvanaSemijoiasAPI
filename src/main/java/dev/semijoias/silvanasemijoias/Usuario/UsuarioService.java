package dev.semijoias.silvanasemijoias.Usuario;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::map)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        return usuario.map(usuarioMapper::map).orElse(null);
    }

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioModel usuario = usuarioMapper.map(usuarioDTO);
        UsuarioModel salvo = usuarioRepository.save(usuario);
        return usuarioMapper.map(salvo);
    }

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            UsuarioModel usuarioExistente = usuario.get();

            usuarioExistente.setNome(usuarioDTO.getNome());
            usuarioExistente.setCpf(usuarioDTO.getCpf());
            usuarioExistente.setEmail(usuarioDTO.getEmail());
            usuarioExistente.setSenha(usuarioDTO.getSenha());
            usuarioExistente.setEndereco(usuarioDTO.getEndereco());
            usuarioExistente.setTipoUsuario(usuarioDTO.getTipoUsuario());

            UsuarioModel atualizado = usuarioRepository.save(usuarioExistente);
            return usuarioMapper.map(atualizado);
        }
        return null;
    }

    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
