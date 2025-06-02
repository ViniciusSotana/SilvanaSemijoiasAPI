package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMaletaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maleta_id", nullable = false)
    private MaletaModel maleta;

    @ManyToOne
    @JoinColumn(name = "joia_id", nullable = false)
    private JoiaModel joia;

    private Integer quantidade;
    private Double precoSugerido;
    private LocalDate dataInsercao;

}

