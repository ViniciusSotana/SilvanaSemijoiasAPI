package dev.semijoias.silvanasemijoias.Administrador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final AdministradorMapper administradorMapper;


    public List<AdministradorDTO> listarAdministrador() {
        List<AdministradorModel> administradores = administradorRepository.findAll();
        return administradores.stream()
                .map(administradorMapper::map)
                .collect(Collectors.toList());
    }

    public AdministradorDTO buscarPorId(Long id) {
        Optional<AdministradorModel> administrador = administradorRepository.findById(id);
        return administrador.map(administradorMapper::map).orElse(null);
    }

    public AdministradorDTO cadastrarAdministrador(AdministradorDTO administradorDTO) {
        AdministradorModel administrador = administradorMapper.map(administradorDTO);
        AdministradorModel salvo = administradorRepository.save(administrador);
        return administradorMapper.map(salvo);
    }


    public AdministradorDTO atualizarAdministrador(Long id, AdministradorDTO administradorDTO) {
        Optional<AdministradorModel> adm = administradorRepository.findById(id);
        if(adm.isPresent()){
            AdministradorModel admExistente = adm.get();

            admExistente.setNome(administradorDTO.getNome());
            admExistente.setEmail(administradorDTO.getEmail());
            admExistente.setTelefone(administradorDTO.getTelefone());
            admExistente.setSenha(administradorDTO.getSenha());
            admExistente.setCpf(administradorDTO.getCpf());
            admExistente.setCargo(administradorDTO.getCargo());
            admExistente.setEndereco(administradorDTO.getEndereco());
            admExistente.setAtivo(administradorDTO.getAtivo());
            admExistente.setId(administradorDTO.getId());
            admExistente.setTipoUsuario(administradorDTO.getTipoUsuario());

            AdministradorModel atualizado = administradorRepository.save(admExistente);

            return administradorMapper.map(atualizado);
        }
        return null;
    }

    public void removerAdministrador(Long id) {
        AdministradorModel adm = administradorRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "administrador nao encontrada"));
        administradorRepository.delete(adm);
    }

}
