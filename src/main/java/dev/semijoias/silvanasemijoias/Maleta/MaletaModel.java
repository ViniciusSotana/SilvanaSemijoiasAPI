package dev.semijoias.silvanasemijoias.Maleta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date dataEntrega;
    private Date dataDevolucao;

}
