package dev.semijoias.silvanasemijoias.ItemMaleta;

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

    private LocalDate dataInsercao;
}

