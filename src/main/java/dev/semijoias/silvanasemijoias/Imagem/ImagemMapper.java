package dev.semijoias.silvanasemijoias.Imagem;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import org.springframework.stereotype.Component;

@Component
public class ImagemMapper {

    public ImagemModel map(ImagemDTO imagemDTO) {
        ImagemModel imagemModel = new ImagemModel();
        imagemModel.setId(imagemDTO.getId());
        imagemModel.setUrlImagem(imagemDTO.getUrlImagem());
        if (imagemDTO.getJoiaId() != null) {
            JoiaModel joia = new JoiaModel();
            joia.setId(imagemDTO.getJoiaId());
            imagemModel.setJoia(joia);
        }

        return imagemModel;
    }

    public ImagemDTO map(ImagemModel imagemModel) {
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setId(imagemModel.getId());
        imagemDTO.setUrlImagem(imagemModel.getUrlImagem());
        if (imagemModel.getJoia() != null) {
            imagemDTO.setJoiaId(imagemModel.getJoia().getId());
        }
        return imagemDTO;
    }
}

