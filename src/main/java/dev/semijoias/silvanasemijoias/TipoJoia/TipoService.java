package dev.semijoias.silvanasemijoias.TipoJoia;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoService {

    private final TipoRepository tipoRepository;
    private final TipoMapper tipoMapper;

    public TipoService(TipoRepository tipoRepository, TipoMapper tipoMapper) {
        this.tipoRepository = tipoRepository;
        this.tipoMapper = tipoMapper;
    }

    public List<TipoDTO> listarTipos() {
        List<TipoModel> tipos = tipoRepository.findAll();
        return tipos.stream()
                .map(tipoMapper::map)
                .collect(Collectors.toList());
    }

    public TipoDTO buscarPorId(Long id) {
        Optional<TipoModel> tipo = tipoRepository.findById(id);
        return tipo.map(tipoMapper::map).orElse(null);
    }

    public TipoDTO cadastrarTipo(TipoDTO tipoDTO) {
        TipoModel tipo = tipoMapper.map(tipoDTO);
        TipoModel salvo = tipoRepository.save(tipo);
        return tipoMapper.map(salvo);
    }


    public TipoDTO atualizarTipo(Long id, TipoDTO tipoDTO) {
        Optional<TipoModel> tipo = tipoRepository.findById(id);
        if(tipo.isPresent()){
            TipoModel tipoExistente = tipo.get();

            tipoExistente.setDescricao(tipoDTO.getDescricao());
            tipoExistente.setQntVendida(tipoDTO.getQntVendida());

            TipoModel atualizado = tipoRepository.save(tipoExistente);

            return tipoMapper.map(atualizado);
        }
        return null;
    }

    public void removerTipo(Long id) {
        tipoRepository.deleteById(id);
    }
}
