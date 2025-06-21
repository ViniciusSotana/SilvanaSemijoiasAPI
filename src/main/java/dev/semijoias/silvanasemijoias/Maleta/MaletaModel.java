package dev.semijoias.silvanasemijoias.Maleta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaModel;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MaletaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "maleta")
    @JsonIgnore
    private VendedoraModel vendedora;
    private String status;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    @OneToMany(mappedBy = "maleta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ItemMaletaModel> itens = new ArrayList<>();

}
