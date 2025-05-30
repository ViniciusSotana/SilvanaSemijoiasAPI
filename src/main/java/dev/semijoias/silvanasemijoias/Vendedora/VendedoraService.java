package dev.semijoias.silvanasemijoias.Vendedora;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedoraService {

    private final VendedoraRepository vendedoraRepository;
    private final VendedoraMapper vendedoraMapper;

    public VendedoraService(VendedoraRepository vendedoraRepository, VendedoraMapper vendedoraMapper) {
        this.vendedoraRepository = vendedoraRepository;
        this.vendedoraMapper = vendedoraMapper;
    }

    public List<VendedoraDTO> listarVendedoras() {
        List<VendedoraModel> vendedoras = vendedoraRepository.findAll();
        return vendedoras.stream()
                .map(vendedoraMapper::map)
                .collect(Collectors.toList());
    }

    public VendedoraDTO buscarPorId(Long id) {
        Optional<VendedoraModel> vendedora = vendedoraRepository.findById(id);
        return vendedora.map(vendedoraMapper::map).orElse(null);
    }

    public VendedoraDTO cadastrarVendedora(VendedoraDTO vendedoraDTO) {
        VendedoraModel vendedora = vendedoraMapper.map(vendedoraDTO);
        VendedoraModel salvo = vendedoraRepository.save(vendedora);
        return vendedoraMapper.map(salvo);
    }

    public VendedoraDTO atualizarVendedora(Long id, VendedoraDTO vendedoraDTO) {
        Optional<VendedoraModel> vendedora = vendedoraRepository.findById(id);
        if(vendedora.isPresent()){
            VendedoraModel vendedoraExistente = vendedora.get();

            vendedoraExistente.setNome(vendedoraDTO.getNome());
            vendedoraExistente.setCpf(vendedoraDTO.getCpf());
            vendedoraExistente.setEmail(vendedoraDTO.getEmail());
            vendedoraExistente.setSenha(vendedoraDTO.getSenha());
            vendedoraExistente.setEndereco(vendedoraDTO.getEndereco());
            vendedoraExistente.setTipoUsuario(vendedoraDTO.getTipoUsuario());
            vendedoraExistente.setIdMaleta(vendedoraDTO.getIdMaleta());
            vendedoraExistente.setComissao(vendedoraDTO.getComissao());

            VendedoraModel atualizado = vendedoraRepository.save(vendedoraExistente);
            return vendedoraMapper.map(atualizado);
        }
        return null;
    }

    public void removerVendedora(Long id) {
        vendedoraRepository.deleteById(id);
    }
    
}
