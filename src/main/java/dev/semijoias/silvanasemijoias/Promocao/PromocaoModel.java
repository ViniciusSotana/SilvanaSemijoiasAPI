package dev.semijoias.silvanasemijoias.Promocao;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double percentualDesconto;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "promocao_id")
    private List<JoiaModel> joias = new ArrayList<>();

}
