package dev.semijoias.silvanasemijoias.Joia;

import org.springframework.stereotype.Component;

@Component
public class JoiaMapper {
    
    public JoiaModel map(JoiaDTO joiaDTO){
        JoiaModel joiaModel = new JoiaModel();
        joiaModel.setId(joiaDTO.getId());
        joiaModel.setValorUnitario(joiaDTO.getValorUnitario());
        joiaModel.setQuantidadeEstoque(joiaDTO.getQuantidadeEstoque());
        joiaModel.setIdTipo(joiaDTO.getIdTipo());
        joiaModel.setQuantidadeVendida(joiaDTO.getQuantidadeVendida());
        joiaModel.setIdColecao(joiaDTO.getIdColecao());
        joiaModel.setImagens(joiaDTO.getImagens());
        return joiaModel;
    }

    public JoiaDTO map(JoiaModel joiaModel) {
        JoiaDTO joiaDTO = new JoiaDTO();
        joiaDTO.setId(joiaModel.getId());
        joiaDTO.setValorUnitario(joiaModel.getValorUnitario());
        joiaDTO.setQuantidadeEstoque(joiaModel.getQuantidadeEstoque());
        joiaDTO.setIdTipo(joiaModel.getIdTipo());
        joiaDTO.setQuantidadeVendida(joiaModel.getQuantidadeVendida());
        joiaDTO.setIdColecao(joiaModel.getIdColecao());
        joiaDTO.setImagens(joiaModel.getImagens());
        return joiaDTO;
    }
    
}
