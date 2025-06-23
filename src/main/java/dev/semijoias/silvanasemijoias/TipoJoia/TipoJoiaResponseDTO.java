package dev.semijoias.silvanasemijoias.TipoJoia;

import dev.semijoias.silvanasemijoias.Joia.JoiaRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoJoiaResponseDTO {
    private Long id;
    private String descricao;
    private Integer quantidadeVendida;
    private List<JoiaRequestDTO> joias;
}