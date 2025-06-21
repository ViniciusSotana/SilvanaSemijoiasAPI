package dev.semijoias.silvanasemijoias.Maleta;

import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaletaDTO {

    private Long id;
    private VendedoraModel vendedora;
    @NotNull(message = "Status obrigatorio")
    private String status;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;


}
