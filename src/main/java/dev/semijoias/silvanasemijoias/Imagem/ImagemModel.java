package dev.semijoias.silvanasemijoias.Imagem;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import jakarta.persistence.*;

@Entity
public class ImagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlImagem;
    @ManyToOne
    @JoinColumn(name = "joia_id")
    private JoiaModel joia;

}
