package dev.semijoias.silvanasemijoias.Vendedora;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
            vendedoraExistente.setEndereco(vendedoraDTO.getEndereco());
            vendedoraExistente.setTipoUsuario(vendedoraDTO.getTipoUsuario());
            vendedoraExistente.setComissao(vendedoraDTO.getComissao());
            vendedoraExistente.setTelefone(vendedoraDTO.getTelefone());
            vendedoraExistente.setEmail(vendedoraDTO.getEmail());


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


    public void gerarVendedorasFake() {

        for (int i = 1; i <= 20; i++) {
            VendedoraModel v = new VendedoraModel();
            v.setNome("Vendedora " + i);
            v.setCpf(String.format("%011d", 10000000000L + (long) (Math.random() * 8999999999L)));
            v.setEmail("vendedora" + i + "@exemplo.com");
            v.setSenha("senha" + i + "123");
            v.setEndereco("Rua Exemplo, " + (10 + i));
            v.setTelefone("9" + String.format("%08d", (int) (Math.random() * 100000000)));
            v.setComissao((double) (5 + (i % 16)));

            vendedoraRepository.save(v);
        }
    }
    
}
