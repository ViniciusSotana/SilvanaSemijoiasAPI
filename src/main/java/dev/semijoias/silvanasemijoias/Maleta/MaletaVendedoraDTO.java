package dev.semijoias.silvanasemijoias.Maleta;

import dev.semijoias.silvanasemijoias.ItemMaleta.ItemMaletaAtualizadoDTO;
import dev.semijoias.silvanasemijoias.Vendedora.VendedoraModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaletaVendedoraDTO {

    private Long id;
    private Long vendedoraId;
    @NotNull(message = "Status obrigatorio")
    private String status;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    private List<Long> itensId;


}
