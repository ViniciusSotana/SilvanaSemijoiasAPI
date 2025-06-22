package dev.semijoias.silvanasemijoias.Colecao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColecaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "colecao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<JoiaModel> joias = new ArrayList<>();
    @Column(name = "dataLancamento")
    private LocalDate dataLancamento;

}
