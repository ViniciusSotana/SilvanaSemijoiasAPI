package dev.semijoias.silvanasemijoias.Imagem;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;
    private final ImagemMapper imagemMapper;

    public ImagemService(ImagemRepository imagemRepository, ImagemMapper imagemMapper) {
        this.imagemRepository = imagemRepository;
        this.imagemMapper = imagemMapper;
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
        ImagemModel salvo = imagemRepository.save(imagem);
        return imagemMapper.map(salvo);
    }


    public ImagemDTO atualizarImagem(Long id, ImagemDTO imagemDTO) {
        Optional<ImagemModel> imagem = imagemRepository.findById(id);
        if(imagem.isPresent()){
            ImagemModel imagemExistente = imagem.get();
            imagemExistente.setUrlImagem(imagemDTO.getUrlImagem());
            imagemExistente.setJoia(imagemDTO.getJoia());
            ImagemModel atualizado = imagemRepository.save(imagemExistente);

            return imagemMapper.map(atualizado);
        }
        return null;
    }

    public void removerImagem(Long id) {
        imagemRepository.deleteById(id);
    }


}
