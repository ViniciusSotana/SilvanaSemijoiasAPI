package dev.semijoias.silvanasemijoias.TipoJoia;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoService {

    private final TipoRepository tipoRepository;

    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public List<TipoRequestDTO> listarTipos() {
        List<TipoModel> tipos = tipoRepository.findAll();
        return tipos.stream()
                .map(TipoMapper::map)
                .collect(Collectors.toList());
    }

    public TipoRequestDTO buscarPorId(Long id) {
        Optional<TipoModel> tipo = tipoRepository.findById(id);
        return tipo.map(TipoMapper::map).orElse(null);
    }

    public TipoRequestDTO cadastrarTipo(TipoRequestDTO tipoDTO) {
        TipoModel tipo = TipoMapper.map(tipoDTO);
        TipoModel salvo = tipoRepository.save(tipo);
        return TipoMapper.map(salvo);
    }


    public TipoRequestDTO atualizarTipo(Long id, TipoRequestDTO tipoDTO) {
        Optional<TipoModel> tipo = tipoRepository.findById(id);
        if(tipo.isPresent()){
            TipoModel tipoExistente = tipo.get();

            tipoExistente.setDescricao(tipoDTO.getDescricao());
            tipoExistente.setQntVendida(tipoDTO.getQntVendida());

            TipoModel atualizado = tipoRepository.save(tipoExistente);

            return TipoMapper.map(atualizado);
        }
        return null;
    }

    public void removerTipo(Long id) {
        tipoRepository.deleteById(id);
    }
}
