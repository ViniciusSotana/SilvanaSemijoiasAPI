package dev.semijoias.silvanasemijoias.Maleta;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaletaService {

    private final MaletaRepository maletaRepository;


    public MaletaService(MaletaRepository maletaRepository) {
        this.maletaRepository = maletaRepository;
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

    public MaletaDTO cadastrarMaleta(MaletaDTO maletaDTO) {
        MaletaModel maleta = MaletaMapper.map(maletaDTO);
        MaletaModel salvo = maletaRepository.save(maleta);
        return MaletaMapper.map(salvo);
    }

    public MaletaDTO atualizarMaleta(Long id, MaletaDTO maletaDTO) {
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
    }

    public void removerMaleta(Long id) {
        maletaRepository.deleteById(id);
    }

}
