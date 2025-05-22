package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoiaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valorUnitario;
    private Integer quantidadeEstoque;
    private Long idTipo;
    private Integer quantidadeVendida;
    private Long idColecao;
    @OneToMany(mappedBy = "joia", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ImagemModel> imagens = new ArrayList<>();

}
