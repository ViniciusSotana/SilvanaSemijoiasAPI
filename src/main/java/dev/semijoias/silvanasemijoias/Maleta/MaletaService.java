package dev.semijoias.silvanasemijoias.Maleta;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoModel;
import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaModel;
import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaRepository;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraRepository;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaletaService {

    private final MaletaRepository maletaRepository;
    private final VendedoraRepository vendedoraRepository;
    private final ItemMaletaRepository itemMaletaRepository;


    public MaletaService(MaletaRepository maletaRepository, VendedoraRepository vendedoraRepository, ItemMaletaRepository itemMaletaRepository) {
        this.maletaRepository = maletaRepository;
        this.vendedoraRepository = vendedoraRepository;
        this.itemMaletaRepository = itemMaletaRepository;
    }

    public List<MaletaDTO> listarMaletas() {
        List<MaletaModel> maletas = maletaRepository.findAll();
        return maletas.stream()
                .map(MaletaMapper::map)
                .collect(Collectors.toList());
    }

    public MaletaDTO buscarPorId(Long id) {
        Optional<MaletaModel> maleta = maletaRepository.findById(id);
        return maleta.map(MaletaMapper::map).orElse(null);
    }

   /* public MaletaDTO cadastrarMaleta(MaletaDTO maletaDTO) {
        MaletaModel maleta = MaletaMapper.map(maletaDTO);
        MaletaModel salvo = maletaRepository.save(maleta);
        return MaletaMapper.map(salvo);
    }*/

    private VendedoraModel buscarVendedora(Long id) {
        return this.vendedoraRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendedora inexistente"));
    }

    private List<ItemMaletaModel> buscarItemMaletas(List<Long> ids) {
        return this.itemMaletaRepository.findAllById(ids);
    }

    public MaletaResponseDTO cadastrarMaleta(MaletaRequestDTO maletaRequestDTO) {
        MaletaModel maleta = MaletaMapper.mapRequest(maletaRequestDTO);

        VendedoraModel vendedora = vendedoraRepository.findById(maletaRequestDTO.getVendedoraId())
                .orElseThrow(() -> new RuntimeException("Vendedora inexistente"));

        if(vendedora.getMaleta()!=null){
            throw new RuntimeException("Vendedora ja tem uma maleta");
        }

        maleta.setVendedora(vendedora);
        vendedora.setMaleta(maleta);

        maletaRepository.save(maleta);
        vendedoraRepository.save(vendedora);
        return MaletaMapper.mapResponse(maleta);
    }

    /*public MaletaDTO atualizarMaleta(Long id, MaletaDTO maletaDTO) {
        Optional<MaletaModel> maleta = maletaRepository.findById(id);
        if(maleta.isPresent()){
            MaletaModel maletaExistente = maleta.get();

            maletaExistente.setStatus(maletaDTO.getStatus());
            maletaExistente.setDataEntrega(maletaDTO.getDataEntrega());
            maletaExistente.setDataDevolucao(maletaDTO.getDataDevolucao());
            maletaExistente.setVendedora(maletaDTO.getVendedora());


            MaletaModel atualizado = maletaRepository.save(maletaExistente);
            return MaletaMapper.map(atualizado);
        }
        return null;
    }*/

    @Transactional
    public MaletaDTO atualizarMaleta(Long id, MaletaVendedoraDTO maletaUpdateDTO) {
        Optional<MaletaModel> maletaOptional = maletaRepository.findById(id);

        if (maletaOptional.isPresent()) {
            MaletaModel maletaExistente = maletaOptional.get();
            if (maletaUpdateDTO.getVendedoraId() != null) {
                VendedoraModel vendedora = vendedoraRepository.findById(maletaUpdateDTO.getVendedoraId())
                        .orElseThrow(() -> new RuntimeException("Vendedora não encontrada com o ID: " + maletaUpdateDTO.getVendedoraId()));

                VendedoraModel vendadedoraAntiga = maletaExistente.getVendedora();
                vendadedoraAntiga.setMaleta(null);

                vendedoraRepository.save(vendadedoraAntiga);

                maletaExistente.setVendedora(vendedora);

                vendedora.setMaleta(maletaExistente);

                vendedoraRepository.save(vendedora);
            }

            maletaExistente.setStatus(maletaUpdateDTO.getStatus());
            maletaExistente.setDataEntrega(maletaUpdateDTO.getDataEntrega());
            maletaExistente.setDataDevolucao(maletaUpdateDTO.getDataDevolucao());

            MaletaModel atualizado = maletaRepository.save(maletaExistente);
            return MaletaMapper.map(atualizado);
        }
        return null;
    }

    public void removerMaleta(Long id) {
        maletaRepository.deleteById(id);
    }

}
