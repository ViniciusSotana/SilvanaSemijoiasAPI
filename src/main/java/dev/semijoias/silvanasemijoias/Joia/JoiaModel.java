package dev.semijoias.silvanasemijoias.Joia;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.semijoias.silvanasemijoias.Colecao.ColecaoModel;
import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import dev.semijoias.silvanasemijoias.Promocao.PromocaoModel;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;
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
    private Double valorOriginal;
    private Double valorVenda;
    private Integer quantidadeEstoque;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promocao_id")
    private PromocaoModel promocao;
    @ManyToOne
    @JoinColumn(name = "tipo")
    private TipoModel tipo;
    private Integer quantidadeVendida;
    @ManyToOne
    @JoinColumn(name = "colecao")
    private ColecaoModel colecao;
    @OneToMany(mappedBy = "joia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ImagemModel> imagens = new ArrayList<>();


    public String getImagemUrl() {
        if (!imagens.isEmpty()) {
            return imagens.get(0).getUrlImagem();
        }
        return null;
    }
}
