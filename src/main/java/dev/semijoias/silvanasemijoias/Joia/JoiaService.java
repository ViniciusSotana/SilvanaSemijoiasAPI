package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoModel;
import dev.semijoias.silvanasemijoias.Colecao.ColecaoRepository;
import dev.semijoias.silvanasemijoias.Colecao.ColecaoResponseDTO;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoJoiaResponseDTO;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoModel;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoiaService {

    private final JoiaRepository joiaRepository;
    private final TipoRepository tipoRepository;
    private final ColecaoRepository colecaoRepository;


    public JoiaService(JoiaRepository joiaRepository, TipoRepository tipoRepository, ColecaoRepository colecaoRepository) {
        this.joiaRepository = joiaRepository;
        this.tipoRepository = tipoRepository;
        this.colecaoRepository = colecaoRepository;
    }

    public List<JoiaResponseDTO> buscarTodas() {
        List<JoiaModel> joiasDoBanco = joiaRepository.findAll(); // ou sua query com JOIN FETCH

        // Converte cada JoiaModel para JoiaResponseDTO
        return joiasDoBanco.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public JoiaRequestDTO buscarPorId(Long id) {
        Optional<JoiaModel> joia = joiaRepository.findById(id);
        return joia.map(JoiaMapper::map).orElse(null);
    }

    public JoiaRequestDTO cadastrarJoia(JoiaRequestDTO joiaRequestDTO) {
        JoiaModel joia = JoiaMapper.map(joiaRequestDTO);

        ColecaoModel colecao = colecaoRepository.findById(joiaRequestDTO.getColecaoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Coleção não encontrada"));
        joia.setColecao(colecao);
        colecao.getJoias().add(joia);

        TipoModel tipo = tipoRepository.findById(joiaRequestDTO.getTipoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo não encontrado"));
        joia.setTipo(tipo);
        tipo.getJoias().add(joia);

        JoiaModel salvo = joiaRepository.save(joia);
        return JoiaMapper.map(salvo);
    }


    public JoiaRequestDTO atualizarJoia(Long id, JoiaRequestDTO joiaRequestDTO) {
        Optional<JoiaModel> joia = joiaRepository.findById(id);
        if(joia.isPresent()){
            JoiaModel joiaExistente = joia.get();

            // Remove da coleção atual, se tiver
            if (joiaExistente.getColecao() != null) {
                joiaExistente.getColecao().getJoias().remove(joiaExistente);
            }

            // Remove do tipo atual, se tiver
            if (joiaExistente.getTipo() != null) {
                joiaExistente.getTipo().getJoias().remove(joiaExistente);
            }

            joiaExistente.setValorOriginal(joiaRequestDTO.getValorOriginal());
            joiaExistente.setQuantidadeEstoque(joiaRequestDTO.getQuantidadeEstoque());
            joiaExistente.setQuantidadeVendida(joiaRequestDTO.getQuantidadeVendida());
            joiaExistente.setImagens(joiaRequestDTO.getImagens());


            ColecaoModel colecao = colecaoRepository.findById(joiaRequestDTO.getColecaoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Coleção não encontrada"));
            joiaExistente.setColecao(colecao);

            TipoModel tipo = tipoRepository.findById(joiaRequestDTO.getTipoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo não encontrado"));
            joiaExistente.setTipo(tipo);

            if(!colecao.getJoias().contains(joiaExistente) ){
                colecao.getJoias().add(joiaExistente);
            }

            if(!tipo.getJoias().contains(joiaExistente) ){
                tipo.getJoias().add(joiaExistente);
            }

            JoiaModel atualizado = joiaRepository.save(joiaExistente);

            return JoiaMapper.map(atualizado);
        }
        return null;
    }

    public void removerJoia(Long id) {
        JoiaModel joia = joiaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joia nao encontrada"));
        if (joia.getColecao() != null) {
            joia.getColecao().getJoias().remove(joia);
        }
        if (joia.getTipo() != null) {
            joia.getTipo().getJoias().remove(joia);
        }
        joiaRepository.delete(joia);
    }

    private JoiaResponseDTO converterParaResponseDTO(JoiaModel joia) {
        JoiaResponseDTO dto = new JoiaResponseDTO();
        dto.setId(joia.getId());
        dto.setValorVenda(joia.getValorVenda());
        dto.setQuantidadeEstoque(joia.getQuantidadeEstoque());
        dto.setImagens(joia.getImagens());

        if (joia.getTipo() != null) {
            TipoJoiaResponseDTO tipoDTO = new TipoJoiaResponseDTO();
            tipoDTO.setId(joia.getTipo().getId());
            tipoDTO.setDescricao(joia.getTipo().getDescricao());
            dto.setTipoJoia(tipoDTO);
        }

        if (joia.getColecao() != null) {
            ColecaoResponseDTO colecaoDTO = new ColecaoResponseDTO();
            colecaoDTO.setId(joia.getColecao().getId());
            colecaoDTO.setNome(joia.getColecao().getNome());
            dto.setColecao(colecaoDTO);
        }

        return dto;
    }

}
