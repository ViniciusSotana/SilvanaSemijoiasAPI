package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Joia.JoiaResponseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMaletaAtualizadoDTO {

    private Long id;

    @NotNull(message = "Joia obrigatória")
    private JoiaResponseDTO joia;

    @NotNull(message = "Quantidade obrigatória")
    private Integer quantidade;

    @NotNull(message = "Data de inserção obrigatória")
    private LocalDate dataInsercao;
}
