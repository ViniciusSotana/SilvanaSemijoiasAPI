package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMaletaDTO {

    private Long id;

    @NotNull(message = "Maleta obrigatória")
    private Long maletaId;

    @NotNull(message = "Joia obrigatória")
    private Long joiaId;

    @NotNull(message = "Quantidade obrigatória")
    private Integer quantidade;

    @NotNull(message = "Preço sugerido obrigatório")
    private Double precoSugerido;

    @NotNull(message = "Data de inserção obrigatória")
    private LocalDate dataInsercao;
}
