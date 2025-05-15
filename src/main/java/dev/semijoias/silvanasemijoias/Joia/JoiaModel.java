package dev.Semijoias.SilvanaSemijoias.Joia;

import dev.Semijoias.SilvanaSemijoias.Imagem.ImagemModel;

import java.util.ArrayList;
import java.util.List;

public class JoiaModel {

    private Long id;
    private Double valorUnitario;
    private Integer quantidadeEstoque;
    private Long idTipo;
    private Integer quantidadeVendida;
    private Long idColecao;
    List<ImagemModel> imagens = new ArrayList<>();

}
