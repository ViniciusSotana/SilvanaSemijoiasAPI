package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoModel;
import dev.semijoias.silvanasemijoias.Colecao.ColecaoRepository;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoModel;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoiaService {

    private final JoiaRepository joiaRepository;
    private final JoiaMapper joiaMapper;
    private final TipoRepository tipoRepository;
    private final ColecaoRepository colecaoRepository;


    public JoiaService(JoiaRepository joiaRepository, JoiaMapper joiaMapper, TipoRepository tipoRepository, ColecaoRepository colecaoRepository) {
        this.joiaRepository = joiaRepository;
        this.joiaMapper = joiaMapper;
        this.tipoRepository = tipoRepository;
        this.colecaoRepository = colecaoRepository;
    }

    @Transactional(readOnly = true)
    public List<JoiaDTO> listarJoia() {
        List<JoiaModel> joias = joiaRepository.findAll();
        return joias.stream()
                .map(joiaMapper::map)
                .collect(Collectors.toList());
    }

    public JoiaDTO buscarPorId(Long id) {
        Optional<JoiaModel> joia = joiaRepository.findById(id);
        return joia.map(joiaMapper::map).orElse(null);
    }

    public JoiaDTO cadastrarJoia(JoiaDTO joiaDTO) {
        JoiaModel joia = joiaMapper.map(joiaDTO);

        ColecaoModel colecao = colecaoRepository.findById(joiaDTO.getColecaoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Coleção não encontrada"));
        joia.setColecao(colecao);
        colecao.getJoias().add(joia);

        TipoModel tipo = tipoRepository.findById(joiaDTO.getTipoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo não encontrado"));
        joia.setTipo(tipo);
        tipo.getJoias().add(joia);

        JoiaModel salvo = joiaRepository.save(joia);
        return joiaMapper.map(salvo);
    }


    public JoiaDTO atualizarJoia(Long id, JoiaDTO joiaDTO) {
        Optional<JoiaModel> joia = joiaRepository.findById(id);
        if(joia.isPresent()){
            JoiaModel joiaExistente = joia.get();

            joiaExistente.setValorUnitario(joiaDTO.getValorUnitario());
            joiaExistente.setQuantidadeEstoque(joiaDTO.getQuantidadeEstoque());
            joiaExistente.setImagens(joiaDTO.getImagens());
            joiaExistente.setQuantidadeVendida(joiaDTO.getQuantidadeVendida());

            // Buscar e setar a nova Coleção
            ColecaoModel colecao = colecaoRepository.findById(joiaDTO.getColecaoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Coleção não encontrada"));
            joiaExistente.setColecao(colecao);

            // Buscar e setar o novo Tipo
            TipoModel tipo = tipoRepository.findById(joiaDTO.getTipoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo não encontrado"));
            joiaExistente.setTipo(tipo);


            JoiaModel atualizado = joiaRepository.save(joiaExistente);

            return joiaMapper.map(atualizado);
        }
        return null;
    }

    public void removerJoia(Long id) {
        joiaRepository.deleteById(id);
    }

}
