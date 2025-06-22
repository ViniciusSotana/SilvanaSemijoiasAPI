package dev.semijoias.silvanasemijoias.Colecao;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColecaoService {

    private final ColecaoRepository colecaoRepository;

    public ColecaoService(ColecaoRepository colecaoRepository) {
        this.colecaoRepository = colecaoRepository;
    }

    public List<ColecaoResponseDTO> listarColecoes() {
        List<ColecaoModel> colecoes = colecaoRepository.findAll();
        return colecoes.stream()
                .map(ColecaoMapper::mapResponse)
                .collect(Collectors.toList());
    }

    public ColecaoRequestDTO buscarPorId(Long id) {
        Optional<ColecaoModel> colecao = colecaoRepository.findById(id);
        return colecao.map(ColecaoMapper::map).orElse(null);
    }

    public ColecaoResponseDTO cadastrarColecao(ColecaoRequestDTO colecaoDTO) {
        ColecaoModel colecao = ColecaoMapper.map(colecaoDTO);
        ColecaoModel salvo = colecaoRepository.save(colecao);
        return ColecaoMapper.mapResponse(salvo);
    }


    public ColecaoRequestDTO atualizarColecao(Long id, ColecaoRequestDTO colecaoDTO) {
        ColecaoModel colecao = colecaoRepository.findById(id)
                .orElse(null);

            colecao.setNome(colecaoDTO.getNome());
            colecao.setJoias(colecaoDTO.getJoias());

            ColecaoModel atualizado = colecaoRepository.save(colecao);

            return ColecaoMapper.map(atualizado);
    }

    public void removerColecao(Long id) {
        colecaoRepository.deleteById(id);
    }
    
}
