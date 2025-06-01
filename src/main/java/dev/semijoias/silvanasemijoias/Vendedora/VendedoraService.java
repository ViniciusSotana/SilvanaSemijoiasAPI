package dev.semijoias.silvanasemijoias.Vendedora;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedoraService {

    private final VendedoraRepository vendedoraRepository;
    private final VendedoraMapper vendedoraMapper;
    private final MaletaRepository maletaRepository;

    public VendedoraService(VendedoraRepository vendedoraRepository, VendedoraMapper vendedoraMapper, MaletaRepository maletaRepository) {
        this.vendedoraRepository = vendedoraRepository;
        this.vendedoraMapper = vendedoraMapper;
        this.maletaRepository = maletaRepository;
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

        if (vendedoraDTO.getMaletaId() != null) {

            MaletaModel maleta = maletaRepository.findById(vendedoraDTO.getMaletaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maleta não encontrada."));
            vendedora.setMaleta(maleta);
            if (maleta != null) {
                maleta.setVendedora(vendedora);
            }
        }

        VendedoraModel salvo = vendedoraRepository.save(vendedora);
        return vendedoraMapper.map(salvo);
    }

    public VendedoraDTO atualizarVendedora(Long id, VendedoraDTO vendedoraDTO) {
        Optional<VendedoraModel> vendedora = vendedoraRepository.findById(id);
        if(vendedora.isPresent()){
            VendedoraModel vendedoraExistente = vendedora.get();

            MaletaModel antigaMaleta = vendedoraExistente.getMaleta();
            if (!antigaMaleta.getId().equals(vendedoraDTO.getMaletaId())) {
                antigaMaleta.setVendedora(null);
            }

            MaletaModel maleta = maletaRepository.findById(vendedoraDTO.getMaletaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Maleta não encontrada"));
            vendedoraExistente.setMaleta(maleta);

            vendedoraExistente.setNome(vendedoraDTO.getNome());
            vendedoraExistente.setCpf(vendedoraDTO.getCpf());
            vendedoraExistente.setEmail(vendedoraDTO.getEmail());
            vendedoraExistente.setSenha(vendedoraDTO.getSenha());
            vendedoraExistente.setEndereco(vendedoraDTO.getEndereco());
            vendedoraExistente.setTipoUsuario(vendedoraDTO.getTipoUsuario());
            vendedoraExistente.setComissao(vendedoraDTO.getComissao());

            VendedoraModel atualizado = vendedoraRepository.save(vendedoraExistente);
            return vendedoraMapper.map(atualizado);
        }
        return null;
    }

    public void removerVendedora(Long id) {

        VendedoraModel vendedora = vendedoraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Vendedora não encontrada"));
        if (vendedora.getMaleta() != null) {
            MaletaModel maleta = vendedora.getMaleta();
            maleta.setVendedora(null);
            vendedora.setMaleta(null);
            vendedoraRepository.save(vendedora);
        }
        vendedoraRepository.deleteById(id);
    }
    
}
