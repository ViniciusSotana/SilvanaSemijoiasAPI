package dev.semijoias.silvanasemijoias.Joia;

import org.springframework.stereotype.Component;

@Component
public class JoiaMapper {
    
    public JoiaModel map(JoiaDTO joiaDTO){
        JoiaModel joiaModel = new JoiaModel();
        joiaModel.setId(joiaDTO.getId());
        joiaModel.setValorUnitario(joiaDTO.getValorUnitario());
        joiaModel.setQuantidadeEstoque(joiaDTO.getQuantidadeEstoque());
        joiaModel.setTipo(joiaDTO.getTipo());
        joiaModel.setQuantidadeVendida(joiaDTO.getQuantidadeVendida());
        joiaModel.setColecao(joiaDTO.getColecao());
        joiaModel.setImagens(joiaDTO.getImagens());
        return joiaModel;
    }

    public JoiaDTO map(JoiaModel joiaModel) {
        JoiaDTO joiaDTO = new JoiaDTO();
        joiaDTO.setId(joiaModel.getId());
        joiaDTO.setValorUnitario(joiaModel.getValorUnitario());
        joiaDTO.setQuantidadeEstoque(joiaModel.getQuantidadeEstoque());
        joiaDTO.setTipo(joiaModel.getTipo());
        joiaDTO.setQuantidadeVendida(joiaModel.getQuantidadeVendida());
        joiaDTO.setColecao(joiaModel.getColecao());
        joiaDTO.setImagens(joiaModel.getImagens());
        return joiaDTO;
    }
    
}
