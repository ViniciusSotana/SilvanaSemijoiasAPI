package dev.semijoias.silvanasemijoias.Imagem;


import org.springframework.stereotype.Component;

@Component
public class ImagemMapper {

    public ImagemModel map(ImagemDTO imagemDTO) {
        ImagemModel imagemModel = new ImagemModel();
        imagemModel.setId(imagemDTO.getId());
        imagemModel.setUrlImagem(imagemDTO.getUrlImagem());
        imagemModel.setJoia(imagemDTO.getJoia());

        return imagemModel;
    }

    public ImagemDTO map(ImagemModel imagemModel) {
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setId(imagemModel.getId());
        imagemDTO.setUrlImagem(imagemModel.getUrlImagem());
        imagemDTO.setJoia(imagemModel.getJoia());

        return imagemDTO;
    }

}
