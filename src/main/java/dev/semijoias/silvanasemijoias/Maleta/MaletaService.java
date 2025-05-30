package dev.semijoias.silvanasemijoias.Maleta;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaletaService {

    private final MaletaRepository maletaRepository;
    private final MaletaMapper maletaMapper;

    public MaletaService(MaletaRepository maletaRepository, MaletaMapper maletaMapper) {
        this.maletaRepository = maletaRepository;
        this.maletaMapper = maletaMapper;
    }

    public List<MaletaDTO> listarMaletas() {
        List<MaletaModel> maletas = maletaRepository.findAll();
        return maletas.stream()
                .map(maletaMapper::map)
                .collect(Collectors.toList());
    }

    public MaletaDTO buscarPorId(Long id) {
        Optional<MaletaModel> maleta = maletaRepository.findById(id);
        return maleta.map(maletaMapper::map).orElse(null);
    }

    public MaletaDTO cadastrarMaleta(MaletaDTO maletaDTO) {
        MaletaModel maleta = maletaMapper.map(maletaDTO);
        MaletaModel salvo = maletaRepository.save(maleta);
        return maletaMapper.map(salvo);
    }

    public MaletaDTO atualizarMaleta(Long id, MaletaDTO maletaDTO) {
        Optional<MaletaModel> maleta = maletaRepository.findById(id);
        if(maleta.isPresent()){
            MaletaModel maletaExistente = maleta.get();



            MaletaModel atualizado = maletaRepository.save(maletaExistente);
            return maletaMapper.map(atualizado);
        }
        return null;
    }

    public void removerMaleta(Long id) {
        maletaRepository.deleteById(id);
    }

}
