package dev.semijoias.silvanasemijoias.Imagem;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import dev.semijoias.silvanasemijoias.Joia.JoiaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;
    private final ImagemMapper imagemMapper;
    private final JoiaRepository joiaRepository;

    public ImagemService(ImagemRepository imagemRepository, ImagemMapper imagemMapper, JoiaService joiaService, JoiaRepository joiaRepository) {
        this.imagemRepository = imagemRepository;
        this.imagemMapper = imagemMapper;
        this.joiaRepository = joiaRepository;
    }

    public List<ImagemDTO> listarImagens() {
        List<ImagemModel> imagens = imagemRepository.findAll();
        return imagens.stream()
                .map(imagemMapper::map)
                .collect(Collectors.toList());
    }

    public ImagemDTO buscarPorId(Long id) {
        Optional<ImagemModel> imagem = imagemRepository.findById(id);
        return imagem.map(imagemMapper::map).orElse(null);
    }

    public ImagemDTO cadastrarImagem(ImagemDTO imagemDTO) {
        ImagemModel imagem = imagemMapper.map(imagemDTO);
        JoiaModel joia = joiaRepository.findById(imagemDTO.getJoiaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joia não encontrada"));
        imagem.setJoia(joia);
        joia.getImagens().add(imagem);

        ImagemModel salvo = imagemRepository.save(imagem);
        return imagemMapper.map(salvo);
    }


    public ImagemDTO atualizarImagem(Long id, ImagemDTO imagemDTO) {
        Optional<ImagemModel> imagem = imagemRepository.findById(id);
        if(imagem.isPresent()){
            ImagemModel imagemExistente = imagem.get();
            imagemExistente.setUrlImagem(imagemDTO.getUrlImagem());

            JoiaModel joia = joiaRepository.findById(imagemDTO.getJoiaId()).
                    orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joia nao encontrada"));

            imagemExistente.setJoia(joia);

            if(!joia.getImagens().contains(imagemExistente) ){
                joia.getImagens().add(imagemExistente);
            }

            ImagemModel atualizado = imagemRepository.save(imagemExistente);

            return imagemMapper.map(atualizado);
        }
        return null;
    }

    public void removerImagem(Long id) {
        ImagemModel imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada"));
        JoiaModel joia = imagem.getJoia();
        if (joia != null) {
            joia.getImagens().remove(imagem); // Remove da lista da joia
        }

        imagemRepository.delete(imagem);

    }


}
