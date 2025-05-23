package dev.semijoias.silvanasemijoias.Imagem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlImagem;
    @ManyToOne
    @JoinColumn(name = "joia_id")
    @JsonBackReference
    private JoiaModel joia;

}
