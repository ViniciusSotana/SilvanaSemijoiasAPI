package dev.semijoias.silvanasemijoias.Joia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.semijoias.silvanasemijoias.Colecao.ColecaoModel;
import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoModel;
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
    @ManyToOne
    @JoinColumn(name = "tipo")
    private TipoModel tipo;
    private Integer quantidadeVendida;
    @ManyToOne
    @JoinColumn(name = "colecao")
    @JsonBackReference
    private ColecaoModel colecao;
    @OneToMany(mappedBy = "joia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ImagemModel> imagens = new ArrayList<>();

}
