package dev.semijoias.silvanasemijoias.TipoJoia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoJoiaResponseDTO {
    private Long id;
    private String descricao;
    private Integer qntVendida;
}