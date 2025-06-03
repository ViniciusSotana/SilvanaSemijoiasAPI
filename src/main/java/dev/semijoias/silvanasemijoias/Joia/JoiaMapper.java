package dev.semijoias.silvanasemijoias.Joia;

import org.springframework.stereotype.Component;

@Component
public class JoiaMapper {
    
    public JoiaModel map(JoiaDTO joiaDTO){
        JoiaModel joiaModel = new JoiaModel();
        joiaModel.setId(joiaDTO.getId());
        joiaModel.setValorOriginal(joiaDTO.getValorOriginal());
        joiaModel.setValorVenda(joiaDTO.getValorVenda());
        joiaModel.setQuantidadeEstoque(joiaDTO.getQuantidadeEstoque());
        joiaModel.setQuantidadeVendida(joiaDTO.getQuantidadeVendida());
        joiaModel.setImagens(joiaDTO.getImagens());
        joiaModel.setPromocao(joiaDTO.getPromocao());
        return joiaModel;
    }

    public JoiaDTO map(JoiaModel joiaModel) {
        JoiaDTO joiaDTO = new JoiaDTO();
        joiaDTO.setId(joiaModel.getId());
        joiaDTO.setValorOriginal(joiaModel.getValorOriginal());
        joiaDTO.setValorVenda(joiaModel.getValorVenda());
        joiaDTO.setQuantidadeEstoque(joiaModel.getQuantidadeEstoque());
        joiaDTO.setQuantidadeVendida(joiaModel.getQuantidadeVendida());
        joiaDTO.setImagens(joiaModel.getImagens());
        if (joiaModel.getTipo() != null) {
            joiaDTO.setTipoId(joiaModel.getTipo().getId());
        }

        if (joiaModel.getColecao() != null) {
            joiaDTO.setColecaoId(joiaModel.getColecao().getId());
        }
        joiaDTO.setPromocao(joiaModel.getPromocao());

        return joiaDTO;
    }
    
}
