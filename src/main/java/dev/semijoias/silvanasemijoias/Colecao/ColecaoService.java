package dev.semijoias.silvanasemijoias.Colecao;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColecaoService {

    private final ColecaoRepository colecaoRepository;
    private final ColecaoMapper colecaoMapper;

    public ColecaoService(ColecaoRepository colecaoRepository, ColecaoMapper colecaoMapper) {
        this.colecaoRepository = colecaoRepository;
        this.colecaoMapper = colecaoMapper;
    }

    public List<ColecaoDTO> listarColecoes() {
        List<ColecaoModel> colecoes = colecaoRepository.findAll();
        return colecoes.stream()
                .map(colecaoMapper::map)
                .collect(Collectors.toList());
    }

    public ColecaoDTO buscarPorId(Long id) {
        Optional<ColecaoModel> colecao = colecaoRepository.findById(id);
        return colecao.map(colecaoMapper::map).orElse(null);
    }

    public ColecaoDTO cadastrarColecao(ColecaoDTO colecaoDTO) {
        ColecaoModel colecao = colecaoMapper.map(colecaoDTO);
        ColecaoModel salvo = colecaoRepository.save(colecao);
        return colecaoMapper.map(salvo);
    }


    public ColecaoDTO atualizarColecao(Long id, ColecaoDTO colecaoDTO) {
        ColecaoModel colecao = colecaoRepository.findById(id)
                .orElse(null);

            colecao.setNome(colecaoDTO.getNome());
            colecao.setJoias(colecaoDTO.getJoias());

            ColecaoModel atualizado = colecaoRepository.save(colecao);

            return colecaoMapper.map(atualizado);
    }

    public void removerColecao(Long id) {
        colecaoRepository.deleteById(id);
    }
    
}
