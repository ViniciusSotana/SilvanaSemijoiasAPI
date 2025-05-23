package dev.semijoias.silvanasemijoias.Joia;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoiaService {

    private final JoiaRepository joiaRepository;
    private final JoiaMapper joiaMapper;


    public JoiaService(JoiaRepository joiaRepository, JoiaMapper joiaMapper) {
        this.joiaRepository = joiaRepository;
        this.joiaMapper = joiaMapper;
    }

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
        JoiaModel salvo = joiaRepository.save(joia);
        return joiaMapper.map(salvo);
    }


    public JoiaDTO atualizarJoia(Long id, JoiaDTO joiaDTO) {
        Optional<JoiaModel> joia = joiaRepository.findById(id);
        if(joia.isPresent()){
            JoiaModel joiaExistente = joia.get();



            JoiaModel atualizado = joiaRepository.save(joiaExistente);

            return joiaMapper.map(atualizado);
        }
        return null;
    }

    public void removerJoia(Long id) {
        joiaRepository.deleteById(id);
    }

}
